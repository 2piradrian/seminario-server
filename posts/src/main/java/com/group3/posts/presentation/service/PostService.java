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
import java.util.Set;
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

    private final CommentRepository commentRepository;


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
        post.setUpvoters(Set.of());
        post.setDownvoters(Set.of());
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
    public GetOnlyPagePostPageRes getPageOnlyPosts(GetOnlyPagePostPageReq dto) {
        PageContent<Post> posts = this.postsRepository.getOnlyPagePosts(dto.getPage(), dto.getSize());

        if (!this.secretKeyHelper.isValid(dto.getSecret())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

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

        return PostMapper.getOnlyPage().toResponse(posts);
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

        PageContent<Post> posts = this.postsRepository.getByProfileIdPage(dto.getProfileId(), dto.getPage(), dto.getSize());;

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

        PageContent<Post> posts = this.postsRepository.getByProfileIdPage(user.getId(), dto.getPage(), dto.getSize());

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
        Set<String> upvoters = post.getUpvoters();
        Set<String> downvoters = post.getDownvoters();

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

        this.notificationsRepository.deleteBySourceId(dto.getToken(), this.secretKeyHelper.getSecret(), post.getId());
        this.commentRepository.deleteAllByPostId(post.getId());
        this.postsRepository.deleteAllUpvoters(post.getId());
        this.postsRepository.deleteAllDownvoters(post.getId());
        this.postsRepository.deleteById(post.getId());
    }

    @Override
    public void deletePostsByUserId(DeletePostsByUserIdReq dto) {
        if (!secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        // Note: Individual post notifications are not deleted here as this is a bulk operation
        // Consider implementing bulk notification deletion if needed
        
        this.postsRepository.deleteUpvotesByUserId(dto.getUserId());
        this.postsRepository.deleteDownvotesByUserId(dto.getUserId());
        this.postsRepository.deleteAllByAuthorId(dto.getUserId());
    }

    @Override
    public void deletePostsByPageId(DeletePostsByPageIdReq dto) {
        if (!secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        // Note: Individual post notifications are not deleted here as this is a bulk operation
        // Consider implementing bulk notification deletion if needed
        
        this.postsRepository.deleteAllByPageId(dto.getPageId());
    }

}
