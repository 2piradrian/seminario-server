package com.group3.auth.data.postgres.mapper;

import com.group3.auth.data.postgres.model.UserModel;
import com.group3.entity.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityMapper {

  public static User toDomain(UserModel userModel){
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

  public static List<User> toDomain(List<UserModel> userModels) {
    if (userModels == null) return Collections.emptyList();

    return userModels.stream()
      .map(UserEntityMapper::toDomain)
      .collect(Collectors.toList());
  }


  public static List<UserModel> toModel(List<User> users) {
    if (users == null) return Collections.emptyList();

    return users.stream()
      .map(UserEntityMapper::toModel)
      .collect(Collectors.toList());
  }

}
