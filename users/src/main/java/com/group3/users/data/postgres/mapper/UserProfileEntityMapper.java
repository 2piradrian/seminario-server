package com.group3.users.data.postgres.mapper;

import com.group3.entity.UserProfile;
import com.group3.users.data.postgres.model.UserProfileModel;

public class UserProfileEntityMapper {

  public static UserProfile toDomain(UserProfileModel userProfileModel){
    return new UserProfile(
      userProfileModel.getId(),
      userProfileModel.getPortraitImage(),
      userProfileModel.getProfileImage(),
      userProfileModel.getShortDescription(),
      userProfileModel.getLongDescription(),
      userProfileModel.getStyles(),
      userProfileModel.getInstruments()
    );
  }

  public static UserProfileModel toModel(UserProfile userProfile){
    return new UserProfileModel(
      userProfile.getId(),
      userProfile.getPortraitImage(),
      userProfile.getProfileImage(),
      userProfile.getShortDescription(),
      userProfile.getLongDescription(),
      userProfile.getStyles(),
      userProfile.getInstruments()
    );
  }

}
