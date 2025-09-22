package com.group3.users.domain.dto.user.response;

import com.group3.entity.Instrument;
import com.group3.entity.Role;
import com.group3.entity.Style;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
public class GetUserByIdRes {

    private final String id;

    private final String name;

    private final String surname;

    private final String email;

    private final List<Role> roles;

    private LocalDateTime memberSince;

    private LocalDateTime lastLogin;

    private final String portraitImage;

    private final String profileImage;

    private final String shortDescription;

    private final String longDescription;

    private final List<Style> styles;

    private final List<Instrument> instruments;

}
