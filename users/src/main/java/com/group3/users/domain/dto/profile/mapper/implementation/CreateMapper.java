package com.group3.users.domain.dto.profile.mapper.implementation;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.entity.UserProfile;
import com.group3.users.domain.dto.profile.request.CreateUserProfileReq;
import com.group3.users.domain.dto.profile.response.CreateUserProfileRes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateMapper {

  public CreateUserProfileReq toRequest(Map<String, Object> payload){

    // Puede haber problemas en caso de que sea un solo string
    Set<Style> styles = ((List<String>) payload.getOrDefault("styles", List.of()))
      .stream()
      .map(Style::valueOf)   // convierte "JAZZ" -> Style.JAZZ
      .collect(Collectors.toSet());

    Set<Instrument> instruments = ((List<String>) payload.getOrDefault("instruments", List.of()))
      .stream()
      .map(Instrument::valueOf)
      .collect(Collectors.toSet());

    return CreateUserProfileReq.create(
      (String) payload.get("portaitImage"),
      (String) payload.get("profileImage"),
      (String) payload.get("shortDescription"),
      (String) payload.get("longDescription"),
      styles,
      instruments
    );
  }

  public CreateUserProfileRes toResponse(UserProfile userProfile) {
    return new CreateUserProfileRes(
      userProfile.getId(),
      userProfile.getPortaitImage(),
      userProfile.getProfileImage(),
      userProfile.getShortDescription(),
      userProfile.getLongDescription(),
      userProfile.getStyles(),
      userProfile.getInstruments()
    );
  }

}
