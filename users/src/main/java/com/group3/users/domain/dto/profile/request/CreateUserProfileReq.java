package com.group3.users.domain.dto.profile.request;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.validator.RegexValidators;
import lombok.Getter;

import java.util.Set;

@Getter
public class CreateUserProfileReq {

  private final String portraitImage;

  private final String profileImage;

  private final String shortDescription;

  private final String longDescription;

  private final Set<Style> styles;

  private final Set<Instrument> instruments;

  private CreateUserProfileReq(
      String portraitImage,
      String profileImage,
      String shortDescription,
      String longDescription,
      Set<Style> styles,
      Set<Instrument> instruments
  ){
      this.portraitImage = portraitImage;
      this.profileImage = profileImage;
      this.shortDescription = shortDescription;
      this.longDescription = longDescription;
      this.styles = styles;
      this.instruments = instruments;
  }

  public static CreateUserProfileReq create(
      String portraitImage,
      String profileImage,
      String shortDescription,
      String longDescription,
      Set<Style> styles,
      Set<Instrument> instruments
  ){

      if (shortDescription == null || longDescription == null || styles == null || instruments == null) {
        throw new ErrorHandler(ErrorType.MISSING_REQUIRED_FIELDS);
      }

      if (shortDescription.isEmpty() || longDescription.isEmpty() || styles.isEmpty() || instruments.isEmpty()) {
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

    return new CreateUserProfileReq(portraitImage, profileImage, shortDescription, longDescription, styles, instruments);
  }

}
