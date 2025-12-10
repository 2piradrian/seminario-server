package com.group3.posts.presentation.service;

import com.group3.config.PrefixedUUID;
import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.config.helpers.SecretKeyHelper;
import com.group3.posts.data.repository.*;
import com.group3.posts.domain.dto.post.mapper.PostMapper;
import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.domain.dto.post.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PostService implements PostServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final PostsRepository postsRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;

    private final PageProfileRepository pageProfileRepository;

    private final NotificationsRepository notificationsRepository;

    private final CatalogRepository catalogRepository;


    // ======== Create Post ========

    @Override
    public CreatePostRes create(CreatePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = new Post();
        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            if (!user.getId().equals(dto.getProfileId())) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            post.setPageProfile(PageProfile.builder().id(null).build());
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            PageProfile page = this.pageProfileRepository.getById(dto.getProfileId(), dto.getToken());
            if (page.getMembers().stream().noneMatch(member -> member.getId().equals(user.getId()))) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            post.setPageProfile(page);
        }

        if (dto.getImage() != null) {
            String imageId = this.imagesRepository.upload(dto.getImage(), secretKeyHelper.getSecret());
            post.setImageId(imageId);
        }

        PostType postType = this.catalogRepository.getByPostTypeId(dto.getPostTypeId());
        if(postType == null) throw new ErrorHandler(ErrorType.POSTYPE_NOT_FOUND);

        post.setPostType(postType);

        post.setId(PrefixedUUID.generate(PrefixedUUID.EntityType.POST).toString());
        post.setAuthor(user);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setStatus(Status.ACTIVE);
        post.setViews(0);
        post.setUpvoters(List.of());
        post.setDownvoters(List.of());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        Post saved = this.postsRepository.save(post);
        return PostMapper.create().toResponse(saved);
    }


    // ======== Get Post by ID ========

    @Override
    public GetPostByIdRes getById(GetPostByIdReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        // Enrich author and page profile
        if (post.getAuthor().getId() != null) {
            User fullProfile = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
            post.setAuthor(fullProfile);
        }
        if (post.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
            post.setPageProfile(fullPage);
        }

        // Increment views
        Integer views = post.getViews();
        post.setViews(views + 1);
        this.postsRepository.update(post);

        post.setVotersToNull();

        return PostMapper.getById().toResponse(post);
    }


    // ======== Get Posts ========

    @Override
    public GetPostPageRes getPosts(GetPostPageReq dto) {
        PageContent<Post> posts = this.postsRepository.getAllPosts(dto.getPage(), dto.getSize());

        // Enrich author and page profile for each post
        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                User fullProfile = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(fullProfile);
            }
            if (post.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                post.setPageProfile(fullPage);
            }
            post.setVotersToNull();
        }

        return PostMapper.getPage().toResponse(posts);
    }

    @Override
    public GetPostByCursorPageRes getPostByCursorPage(GetPostByCursorPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        CursorContent<Post> posts = this.postsRepository.getByCursorPage(dto.getCursor(), dto.getSize(), dto.getProfileId());

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                User fullAuthor = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(fullAuthor);
            }
            if (post.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                post.setPageProfile(fullPage);
            }
            post.setVotersToNull();
        }

        return PostMapper.getPostByCursor().toResponse(posts);
    }

    // ======== Get Filtered Posts ========

    @Override
    public GetFilteredPostPageRes getFilteredPosts(GetFilteredPostPageReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Post> posts = this.postsRepository.getFilteredPosts(dto.getPage(), dto.getSize(), dto.getText(), dto.getPostTypeId());

        return PostMapper.getFilteredPage().toResponse(posts);
    }


    // ======== Get Posts by Profile ========

    @Override
    public GetPostPageByProfileRes getPostsByProfile(GetPostPageByProfileReq dto) {

        PageContent<Post> posts = null;

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            posts = this.postsRepository.getPostsByAuthorId(dto.getProfileId(), dto.getPage(), dto.getSize());
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            posts = this.postsRepository.getPostsByPageId(dto.getProfileId(), dto.getPage(), dto.getSize());
        }

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                User fullProfile = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(fullProfile);
            }
            if (post.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                post.setPageProfile(fullPage);
            }
            post.setVotersToNull();
        }

        return PostMapper.getPageByProfile().toResponse(posts);
    }


    // ======== Get Own Posts ========

    @Override
    public GetOwnPostPageRes getOwnPosts(GetOwnPostPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Post> posts = this.postsRepository.getPostsByAuthorId(user.getId(), dto.getPage(), dto.getSize());

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                User fullProfile = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
                post.setAuthor(fullProfile);
            }
            if (post.getPageProfile().getId() != null) {
                PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
                post.setPageProfile(fullPage);
            }
            post.setVotersToNull();
        }

        return PostMapper.getOwnPage().toResponse(posts);
    }


    // ======== Toggle Post Votes ========

    @Override
    public TogglePostVotesRes toggleVotes(TogglePostVotesReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        String userId = user.getId();
        List<String> upvoters = post.getUpvoters();
        List<String> downvoters = post.getDownvoters();

        boolean isNewUpvote = false;
        boolean isNewDownvote = false;

        if (Vote.UPVOTE == dto.getVoteType()) {
            if (upvoters.contains(userId)) {
                upvoters.remove(userId);
            }
            else {
                upvoters.add(userId);
                downvoters.remove(userId);
                isNewUpvote = true;
            }
        }
        if (Vote.DOWNVOTE == dto.getVoteType()) {
            if (downvoters.contains(userId)) {
                downvoters.remove(userId);
            }
            else {
                downvoters.add(userId);
                upvoters.remove(userId);
                isNewDownvote = true;
            }
        }

        post.setUpvoters(upvoters);
        post.setDownvoters(downvoters);

        this.postsRepository.update(post);

        String targetId;
        if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
            targetId = post.getPageProfile().getId();
        }
        else {
            targetId = post.getAuthor().getId();
        }

        if (isNewUpvote) {
            this.notificationsRepository.create(
                    this.secretKeyHelper.getSecret(),
                    targetId,
                    post.getId(),
                    user.getId(),
                    NotificationContent.UPVOTE.name()
            );
        }
        else if (isNewDownvote) {
            this.notificationsRepository.create(
                    this.secretKeyHelper.getSecret(),
                    targetId,
                    post.getId(),
                    user.getId(),
                    NotificationContent.DOWNVOTE.name()
            );
        }

        if (post.getAuthor() != null && post.getAuthor().getId() != null) {
            User fullProfile = this.userRepository.getById(post.getAuthor().getId(), dto.getToken());
            post.setAuthor(fullProfile);
        }

        if (post.getPageProfile() != null && post.getPageProfile().getId() != null) {
            PageProfile fullPage = this.pageProfileRepository.getById(post.getPageProfile().getId(), dto.getToken());
            post.setPageProfile(fullPage);
        }

        post.calculateVotersQuantity();
        post.setVotersToNull();

        return PostMapper.toggleVotes().toResponse(post);
    }


    // ======== Edit Post ========
    @Override
    public EditPostRes edit(EditPostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (!post.getAuthor().getId().equals(user.getId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (dto.getBase64Image() != null) {
            String postImage = post.getImageId();
            if (postImage != null) {
                this.imagesRepository.delete(postImage, secretKeyHelper.getSecret());
            }
            String imageId = this.imagesRepository.upload(dto.getBase64Image(), secretKeyHelper.getSecret());
            post.setImageId(imageId);
        }

        PostType postType = this.catalogRepository.getByPostTypeId(dto.getPostTypeId());
        if(postType == null) throw new ErrorHandler(ErrorType.POSTYPE_NOT_FOUND);

        post.setPostType(postType);

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        Post edited = this.postsRepository.update(post);
        return PostMapper.edit().toResponse(edited);
    }


    // ======== Delete Post ========

    @Override
    public void delete(DeletePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (!user.canDelete(post)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        post.setUpdatedAt(LocalDateTime.now());
        post.setStatus(Status.DELETED);

        this.postsRepository.update(post);
    }

}
