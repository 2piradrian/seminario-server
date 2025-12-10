package com.group3.results.config.helpers;

import com.group3.entity.Event;
import com.group3.entity.Post;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedUtil {

    public static LocalDateTime getCreatedAt(Object obj) {

        if (obj instanceof Event event) {
            return event.getCreatedAt();
        } else if (obj instanceof Post post) {
            return post.getCreatedAt();
        }

        throw new ErrorHandler(ErrorType.INTERNAL_ERROR);
    }

}
