package com.group3.users.domain.dto.user.mapper.implementation;

import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.RegisterUserReq;
import com.group3.users.domain.dto.user.response.RegisterUserRes;

import java.util.Map;

public class RegisterMapper {

  public RegisterUserReq toRequest(Map<String, Object> payload) {
    return RegisterUserReq.create(
      (String) payload.get("name"),
      (String) payload.get("surname"),
      (String) payload.get("password"),
      (String) payload.get("email")
    );

  }

  public RegisterUserRes toResponse(User user) {
    return new RegisterUserRes(
      user.getId(),
      user.getName(),
      user.getSurname(),
      user.getEmail(),
      user.getRoles(),
      user.getMemberSince(),
      user.getLastLogin()
    );
  }

}
