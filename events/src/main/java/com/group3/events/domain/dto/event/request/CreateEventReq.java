package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.events.domain.validator.RegexValidators;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Getter
public class CreateEventReq {

    private final String token;

    private final String title;

    private final String content;

    private final String profileId;

    private final String image;

    private final Date dateInit;

    private final Date dateEnd;

    private CreateEventReq(String token, String title, String content, String profileId, String image, Date dateInit, Date dateEnd) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.profileId = profileId;
        this.image = image;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
    }

    public static CreateEventReq create(String token, String title, String content, String profileId, String image, Date dateInit, Date dateEnd) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (title == null || content == null || profileId == null || dateInit == null || dateEnd == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        title = title.trim();
        if (title.isEmpty() || !title.matches(RegexValidators.TITLE.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        content = content.trim();
        if (content.isEmpty() || !content.matches(RegexValidators.CONTENT.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (dateInit.after(dateEnd)) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        LocalDate localDateInit = dateInit.toInstant().atZone(ZoneOffset.UTC).toLocalDate();

        if(localDateInit.isBefore(LocalDate.now(ZoneOffset.UTC))){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new CreateEventReq(token, title, content, profileId, image, dateInit, dateEnd);
    }
}
