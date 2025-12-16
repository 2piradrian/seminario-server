package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String email;

    private String password;

    private Status status;

    private Role role;

    private UserProfile profile;

    private boolean isPageMember(PageProfile pageProfile) {
        return pageProfile != null
                && pageProfile.getMembers() != null
                && pageProfile.getMembers().stream()
                .anyMatch(member -> member.getId().equals(this.getId()));
    }

    public boolean canDelete(Post post){
        return this.role.canDelete() ||
        (post.getAuthor() != null && post.getAuthor().getId().equals(this.getId())) ||
        this.isPageMember(post.getPageProfile());
    }

    public boolean canDelete(Event event){
        return this.role.canDelete() ||
            (event.getAuthor() != null && event.getAuthor().getId().equals(this.getId())) ||
            this.isPageMember(event.getPageProfile());
    }

    public boolean canDelete(PageProfile page){
        return this.role.canDelete() || (page.getOwner()!= null && page.getOwner().getId().equals(this.getId()));
    }

    public boolean canDelete(Comment comment){
        return this.role.canDelete() ||
                (comment.getAuthor() != null && comment.getAuthor().getId().equals(this.getId())) ||
                this.isPageMember(comment.getPageProfile());
    }

    public boolean canDelete(Review review){
        return this.role.canDelete() ||
            (review.getReviewerUser() != null && review.getReviewerUser().getId().equals(this.getId())) ||
            (review.getReviewedId().equals(this.getId()));
    }

}
