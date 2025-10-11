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

    private final PagesRepository pagesRepository;

    private final ProfilesRepository profilesRepository;

    @Override
    public GetPostByIdRes getById(GetPostByIdReq dto) {
        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (post.getAuthor().getId() != null) {
            UserProfile fullProfile = this.profilesRepository.getById(post.getAuthor().getId());
            post.setAuthor(fullProfile);
        }
        if (post.getPage().getId() != null) {
            Page fullPage = this.pagesRepository.getById(post.getPage().getId());
            post.setPage(fullPage);
        }

        Integer views = post.getViews();
        post.setViews(views + 1);

        this.postsRepository.update(post);

        return PostMapper.getById().toResponse(post);
    }

    @Override
    public GetPostPageRes getPosts(GetPostPageReq dto) {
        PageContent<Post> posts = this.postsRepository.getAllPosts(dto.getPage(), dto.getSize());

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                UserProfile fullProfile = this.profilesRepository.getById(post.getAuthor().getId());
                post.setAuthor(fullProfile);
            }
            if (post.getPage().getId() != null) {
                Page fullPage = this.pagesRepository.getById(post.getPage().getId());
                post.setPage(fullPage);
            }
        }

        return PostMapper.getPage().toResponse(posts);
    }

    @Override
    public GetPostPageByProfileRes getPostsByProfile(GetPostPageByProfileReq dto) {
        PageContent<Post> posts = this.postsRepository.getPostsByUserId(dto.getProfileId(), dto.getPage(), dto.getSize());

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                UserProfile fullProfile = this.profilesRepository.getById(post.getAuthor().getId());
                post.setAuthor(fullProfile);
            }
            if (post.getPage().getId() != null) {
                Page fullPage = this.pagesRepository.getById(post.getPage().getId());
                post.setPage(fullPage);
            }
        }

        return PostMapper.getPageByProfile().toResponse(posts);
    }

    @Override
    public GetOwnPostPageRes getOwnPosts(GetOwnPostPageReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        PageContent<Post> posts = this.postsRepository.getPostsByUserId(user.getId(), dto.getPage(), dto.getSize());

        for (Post post : posts.getContent()) {
            if (post.getAuthor().getId() != null) {
                UserProfile fullProfile = this.profilesRepository.getById(post.getAuthor().getId());
                post.setAuthor(fullProfile);
            }
            if (post.getPage().getId() != null) {
                Page fullPage = this.pagesRepository.getById(post.getPage().getId());
                post.setPage(fullPage);
            }
        }

        return PostMapper.getOwnPage().toResponse(posts);
    }

    @Override
    public CreatePostRes create(CreatePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        UserProfile author = UserProfile.builder().id(user.getId()).build();
        Post post = new Post();

        PrefixedUUID.EntityType type = PrefixedUUID.resolveType(UUID.fromString(dto.getProfileId()));
        if (type == PrefixedUUID.EntityType.USER) {
            if (!user.getId().equals(dto.getProfileId())) throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            post.setPage(Page.builder().id(null).build());
            post.setAuthor(author);
        }
        else if (type == PrefixedUUID.EntityType.PAGE) {
            Page page = this.pagesRepository.getById(dto.getProfileId());

            if (page.getMembers().stream().noneMatch(member -> member.getId().equals(user.getId()))) {
                throw new ErrorHandler(ErrorType.UNAUTHORIZED);
            }
            post.setAuthor(author);
            post.setPage(page);
        }

        if (dto.getImage() != null) {
            String imageId = this.imagesRepository.upload(dto.getImage(), secretKeyHelper.getSecret());
            post.setImageId(imageId);
        }

        post.setAuthor(author);
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

    @Override
    public EditPostRes edit(EditPostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (!post.getAuthor().getId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (post.getPage() != null){
            // TODO: Search page and verify if is member
        }

        if (dto.getBase64Image() != null){
            String postImage = post.getImageId();
            if (postImage != null) {
                this.imagesRepository.delete(postImage, secretKeyHelper.getSecret());
            }

            String imageId = this.imagesRepository.upload(dto.getBase64Image(), secretKeyHelper.getSecret());
            post.setImageId(imageId);
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        Post edited = this.postsRepository.update(post);
        return PostMapper.edit().toResponse(edited);
    }

    @Override
    public TogglePostVotesRes toggleVotes(TogglePostVotesReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        String userId = user.getId();

        List<String> upvoters = post.getUpvoters();
        List<String> downvoters = post.getDownvoters();

        if (Vote.UPVOTE == dto.getVoteType()) {
            if (upvoters.contains(userId)) {
                upvoters.remove(userId);
            }
            else {
                upvoters.add(userId);
                downvoters.remove(userId);
            }
        }
        if (Vote.DOWNVOTE == dto.getVoteType()) {
            if (downvoters.contains(userId)) {
                downvoters.remove(userId);
            }
            else {
                downvoters.add(userId);
                upvoters.remove(userId);
            }
        }

        post.setUpvoters(upvoters);
        post.setDownvoters(downvoters);

        this.postsRepository.update(post);

        if (post.getAuthor() != null && post.getAuthor().getId() != null) {
            UserProfile fullProfile = this.profilesRepository.getById(post.getAuthor().getId());
            post.setAuthor(fullProfile);
        }

        if (post.getPage() != null && post.getPage().getId() != null) {
            Page fullPage = this.pagesRepository.getById(post.getPage().getId());
            post.setPage(fullPage);
        }

        return PostMapper.toggleVotes().toResponse(post);
    }

    @Override
    public void delete(DeletePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postsRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        boolean isAuthor = post.getAuthor().getId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains(Role.ADMIN);
        boolean isModerator = user.getRoles().contains(Role.MODERATOR);

        if (!isAuthor && !isAdmin && !isModerator) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        post.setUpdatedAt(LocalDateTime.now());
        post.setStatus(Status.DELETED);

        this.postsRepository.update(post);
    }

}
