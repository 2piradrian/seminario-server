package com.group3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentType {

    private String id;

    private String name;

    public boolean isPageType(){
        return this.id.equals("pageprofile");
    }

    public boolean isPostType(){
        return this.id.equals("post");
    }

    public boolean isUserType(){
        return this.id.equals("userprofile");
    }

    public boolean isEventType(){
        return this.id.equals("event");
    }

}
