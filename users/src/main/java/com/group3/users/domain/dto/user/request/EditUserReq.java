package com.group3.users.domain.dto.user.request;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

import java.util.List;

@Getter
public class EditUserReq {

    private final String token;

    private final String name;

    private final String surname;

    private final String portraitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final List<Style> styles;

    private final List<Instrument> instruments;

    private EditUserReq(
        String token,
        String name,
        String surname,
        String portraitImage,
        String profileImage,
        String shortDescription,
        String longDescription,
        List<Style> styles,
        List<Instrument> instruments
    ){
        this.token = token;
        this.name = name;
        this.surname = surname;
        this.portraitImage = portraitImage;
        this.profileImage = profileImage;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.styles = styles;
        this.instruments = instruments;
    }

    public static EditUserReq create(
        String token,
        String name,
        String surname,
        String portraitImage,
        String profileImage,
        String shortDescription,
        String longDescription,
        List<Style> styles,
        List<Instrument> instruments
    ){
        if (token == null){
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (name == null || surname == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (name.isEmpty() || surname.isEmpty()) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators nameValidator = RegexValidators.NAME;
        if (!name.matches(nameValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators surnameValidator = RegexValidators.SURNAME;
        if (!surname.matches(surnameValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (shortDescription == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (shortDescription.isEmpty()){
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (longDescription == null){
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        if (longDescription.isEmpty()) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        if (styles == null || instruments == null) {
            throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        RegexValidators shortDescriptionValidator = RegexValidators.SHORT_DESCRIPTION;
        if (!shortDescription.matches(shortDescriptionValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        RegexValidators longDescriptionValidator = RegexValidators.LONG_DESCRIPTION;
        if (!longDescription.matches(longDescriptionValidator.getRegex())) {
            throw new ErrorHandler(ErrorType.INVALID_FIELDS);
        }

        return new EditUserReq(token, name, surname , portraitImage, profileImage, shortDescription, longDescription, styles, instruments);
    }

}
