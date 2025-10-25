package com.group3.users.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.data.repository.UserProfileRepository;
import com.group3.users.data.repository.UserRepository;
import com.group3.users.domain.dto.auth.request.AuthUserReq;
import com.group3.users.domain.dto.auth.response.AuthUserRes;
import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.domain.dto.user.response.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserServiceI {

    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    private final AuthService authService;

    @Override
    public GetUserByIdRes getById(GetUserByIdReq dto) {
        User user = this.userRepository.getById(dto.getUserId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        return UserMapper.getById().toResponse(user);
    }

    @Override
    public GetAllStaffRes getAllStaff(GetAllStaffReq dto){
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        if (!auth.getRole().canAsignRole()) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        List<User> staffUsers = this.userRepository.getAllStaff();

        Map<Role,List<UserProfile>> staff = new HashMap<>();
        List<UserProfile> modUsers = new ArrayList<>();
        List<UserProfile> adminUsers = new ArrayList<>();

        for (User user : staffUsers){
            UserProfile userProfile = this.userProfileRepository.getById(dto.getToken(), user.getId());
            if (user.getRole().equals(Role.MODERATOR)){
                modUsers.add(userProfile);
            } else if (user.getRole().equals(Role.ADMIN)){
                adminUsers.add(userProfile);
            }
        }

        staff.put(Role.ADMIN,adminUsers);
        staff.put(Role.MODERATOR,modUsers);

        return UserMapper.getAllStaff().toResponse(staff);
    }

    // TODO: verificar si ya no esta deleted

    @Override
    public void delete(DeleteUserReq dto) {
        AuthUserRes auth = this.authService.auth(AuthUserReq.create(dto.getToken()));
        if (auth == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        User user = this.userRepository.getById(auth.getId());
        if (user == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        user.setStatus(Status.DELETED);

        this.userRepository.save(user);
    }

}
