package com.group3.events.domain.dto.event.request;

import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.events.domain.validator.RegexValidators;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class EditEventReq {

    private final String token;

    private final String eventId;

    private final String title;

    private final String content;

    private final String base64Image;

    private final Date dateInit;

    private final Date dateEnd;

    private EditEventReq(String token, String eventId, String title, String content, String base64Image, Date dateInit, Date dateEnd) {
        this.token = token;
        this.eventId = eventId;
        this.title = title;
        this.content = content;
        this.base64Image = base64Image;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
    }

    public static EditEventReq create(String token, String eventId, String title, String content, String base64Image, Date dateInit, Date dateEnd) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (eventId == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (title == null || content == null || dateInit == null || dateEnd == null) {
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

        return new EditEventReq(token, eventId, title, content, base64Image, dateInit, dateEnd);
    }

}
