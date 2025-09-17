package com.group3.auth.data.postgres.mapper;

import com.group3.auth.data.postgres.model.UserModel;
import com.group3.entity.User;

public class UserEntityMapper {

  public static User toDomian(UserModel userModel){
    return new User(
      userModel.getId(),
      userModel.getName(),
      userModel.getSurname(),
      userModel.getPassword(),
      userModel.getEmail(),
      userModel.getMemberSince(),
      userModel.getLastLogin(),
      userModel.getRoles(),
      userModel.getStatus()
    );
  }

  public static UserModel toModel(User user){
    return new UserModel(
      user.getId(),
      user.getName(),
      user.getSurname(),
      user.getEmail(),
      user.getPassword(),
      user.getMemberSince(),
      user.getLastLogin(),
      user.getRoles(),
      user.getStatus()
    );
  }

}
