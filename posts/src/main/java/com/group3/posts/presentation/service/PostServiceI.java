package com.group3.posts.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.posts.data.repository.AuthRepository;
import com.group3.posts.data.repository.PostRepository;
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

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PostServiceI implements PostService {

    private final PostRepository postRepository;
    private final AuthRepository authRepository;

    @Override
    public GetPostByIdRes getById(GetPostByIdReq dto) {
        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        User author = this.authRepository.getById(post.getAuthorId());
        if(author == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        Integer views = post.getViews();
        post.setViews(views + 1);

        this.postRepository.update(post);

        return PostMapper.getById().toResponse(post, author);
    }

    @Override
    public GetPostPageRes getPosts(GetPostPageReq dto) {
        PageContent<Post> posts =
                this.postRepository.getAllPosts(dto.getPage(), dto.getSize(), dto.getCategory());

        return PostMapper.getPage().toResponse(posts);
    }

    @Override
    public GetMonthlyPostsRes getMonthlyPosts(GetMonthlyPostReq dto) {
        TokenClaims claims = this.authRepository.auth(dto.getToken());

        if (claims == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        boolean isAdmin = claims.getRoles().contains(Role.ADMIN);
        if (!isAdmin) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<Post> posts =
                this.postRepository.getMonthlyPosts(dto.getMonth(), dto.getYear());

        return PostMapper.getMonthly().toResponse(posts);
    }

    @Override
    public CreatePostRes create(CreatePostReq dto) {
        TokenClaims claims = this.authRepository.auth(dto.getToken());
        if (claims == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = new Post();

        post.setAuthorId(claims.getId());
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
        TokenClaims claims = this.authRepository.auth(dto.getToken());
        if (claims == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        if (!post.getAuthorId().equals(claims.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCategory(dto.getCategory());
        post.setUpdatedAt(LocalDateTime.now());

        Post edited = this.postRepository.update(post);
        return PostMapper.edit().toResponse(edited);
    }

    @Override
    public void toggleVotes(TogglePostVotesReq dto) {
        TokenClaims claims = this.authRepository.auth(dto.getToken());
        if (claims == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        String user = claims.getId();

        Set<String> upvoters = post.getUpvoters();
        Set<String> downvoters = post.getDownvoters();

        if (Vote.UPVOTE == dto.getVoteType()) {
            if (upvoters.contains(user)) {
                upvoters.remove(user);
            } else {
                upvoters.add(user);
                downvoters.remove(user);
            }
        }
        if (Vote.DOWNVOTE == dto.getVoteType()) {
            if (downvoters.contains(user)) {
                downvoters.remove(user);
            } else {
                downvoters.add(user);
                upvoters.remove(user);
            }
        }

        post.setUpvoters(upvoters);
        post.setDownvoters(downvoters);

        this.postRepository.update(post);
    }

    @Override
    public void delete(DeletePostReq dto) {
        TokenClaims claims = this.authRepository.auth(dto.getToken());
        if (claims == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Post post = this.postRepository.getById(dto.getPostId());
        if (post == null) throw new ErrorHandler(ErrorType.POST_NOT_FOUND);

        boolean isAuthor = post.getAuthorId().equals(claims.getId());
        boolean isAdmin = claims.getRoles().contains(Role.ADMIN);
        boolean isModerator = claims.getRoles().contains(Role.MODERATOR);

        if (!isAuthor && !isAdmin && !isModerator) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        post.setUpdatedAt(LocalDateTime.now());
        post.setStatus(Status.DELETED);

        this.postRepository.update(post);
    }
}
