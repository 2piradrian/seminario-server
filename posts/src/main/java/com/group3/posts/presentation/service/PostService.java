package com.group3.posts.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.config.helpers.SecretKeyHelper;
import com.group3.posts.data.repository.ImagesRepository;
import com.group3.posts.data.repository.PostRepository;
import com.group3.posts.data.repository.UserRepository;
import com.group3.posts.domain.dto.post.mapper.PostMapper;
import com.group3.posts.domain.dto.post.request.*;
import com.group3.posts.domain.dto.post.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PostService implements PostServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ImagesRepository imagesRepository;

    @Override
    public GetPostByIdRes getById(GetPostByIdReq dto) {
        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        User author = this.userRepository.getById(post.getAuthorId());
        if(author == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        Integer views = post.getViews();
        post.setViews(views + 1);

        this.postRepository.update(post);

        // TODO: Search page
        return PostMapper.getById().toResponse(post, author, new Page());
    }

    @Override
    public GetPostPageRes getPosts(GetPostPageReq dto) {
        PageContent<Post> posts =
                this.postRepository.getAllPosts(dto.getPage(), dto.getSize());

        return PostMapper.getPage().toResponse(posts);
    }

    @Override
    public CreatePostRes create(CreatePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = new Post();

        if (dto.getBase64Image() != null){
            String profileId = this.imagesRepository.upload(dto.getBase64Image(), secretKeyHelper.getSecret());
            post.setImageId(profileId);
        }
        // TODO: Search page and verify if is member
        post.setAuthorId(user.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCategory(dto.getCategory());
        post.setStatus(Status.ACTIVE);

        post.setViews(0);
        post.setUpvoters(Set.of());
        post.setDownvoters(Set.of());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        Post saved = this.postRepository.save(post);

        return PostMapper.create().toResponse(saved);
    }

    @Override
    public EditPostRes edit(EditPostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (!post.getAuthorId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }
        // TODO: Search page and verify if is member
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCategory(dto.getCategory());
        post.setUpdatedAt(LocalDateTime.now());

        Post edited = this.postRepository.update(post);
        return PostMapper.edit().toResponse(edited);
    }

    @Override
    public void toggleVotes(TogglePostVotesReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        String userId = user.getId();

        Set<String> upvoters = post.getUpvoters();
        Set<String> downvoters = post.getDownvoters();

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

        this.postRepository.update(post);
    }

    @Override
    public void delete(DeletePostReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        boolean isAuthor = post.getAuthorId().equals(user.getId());
        boolean isAdmin = user.getRoles().contains(Role.ADMIN);
        boolean isModerator = user.getRoles().contains(Role.MODERATOR);

        if (!isAuthor && !isAdmin && !isModerator) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        post.setUpdatedAt(LocalDateTime.now());
        post.setStatus(Status.DELETED);

        this.postRepository.update(post);
    }

}
