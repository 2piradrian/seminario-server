package com.group3.pages.domain.dto.request;

import com.group3.entity.PageType;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.pages.domain.validator.RegexValidators;
import lombok.Getter;

import java.util.List;

@Getter
public class EditPageReq {
    
    private final String token;

    private final String pageId;

    private final String name;

    private final String portraitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final List<String> members;

    private final PageType pageType;

    private EditPageReq(
        String token,
        String pageId,
        String name,
        String portraitImage,
        String profileImage,
        String shortDescription,
        String longDescription,
        List<String> members,
        PageType pageType
    ) {
        this.token = token;
        this.pageId = pageId;
        this.name = name;
        this.portraitImage = portraitImage;
        this.profileImage = profileImage;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.members = members;
        this.pageType = pageType;
    }

    public static EditPageReq create(
        String token,
        String pageId,
        String name,
        String portraitImage,
        String profileImage,
        String shortDescription,
        String longDescription,
        String ownerId,
        List<String> members,
        PageType pageType
    ) {

        if (token == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (name.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators nameValidator = RegexValidators.NAME;
        if (!name.matches(nameValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (pageId == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (pageId.isEmpty()){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (shortDescription == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (shortDescription.isEmpty() || shortDescription.length() > 256) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (longDescription == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (longDescription.isEmpty() || longDescription.length() > 4096) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators shortDescriptionValidator = RegexValidators.SHORT_DESCRIPTION;
        if (!shortDescription.matches(shortDescriptionValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators longDescriptionValidator = RegexValidators.LONG_DESCRIPTION;
        if (!longDescription.matches(longDescriptionValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (members == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if(!members.contains(ownerId)){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (pageType == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return new EditPageReq(
            token,
            pageId,
            name,
            portraitImage,
            profileImage,
            shortDescription,
            longDescription,
            members,
            pageType
        );
    }
    
}
