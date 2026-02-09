package com.group3.users.presentation.service;

import com.group3.entity.BannedUser;
import com.group3.entity.PageContent;
import com.group3.entity.Role;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.dto.banneduser.mapper.BannedUserMapper;
import com.group3.users.domain.dto.banneduser.request.BanUserReq;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;
import com.group3.users.domain.dto.user.request.DeleteUserReq;
import com.group3.users.domain.repository.BannedRepositoryI;
import com.group3.users.domain.repository.UserRepositoryI;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class BannedService implements BannedServiceI {

    private final AuthService authService;
    private final BannedRepositoryI bannedRepository;
    private final UserRepositoryI userRepository;
    private final UserService userService;

    @Override
    public void banUser(BanUserReq banUserReq) {
        User adminUser = this.authService.auth(banUserReq.getToken());
        if (adminUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        User userToBan = this.userRepository.getById(banUserReq.getUserId());
        if (userToBan == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        BannedUser bannedUser = new BannedUser();
        bannedUser.setBannedBy(adminUser);
        bannedUser.setEmail(userToBan.getEmail());
        //bannedUser.setReason(banUserReq.getReason());
        bannedUser.setCreatedAt(LocalDateTime.now());
        bannedUser.setUpdatedAt(LocalDateTime.now());

        bannedRepository.save(bannedUser);

        this.userService.deleteById(banUserReq.getToken(), banUserReq.getUserId());
    }

    @Override
    public GetAllBannedUserPageRes getAllBannedUsers(GetAllBannedUserPageReq getAllBannedUserPageReq) {
        User adminUser = this.authService.auth(getAllBannedUserPageReq.getToken());
        if (adminUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<BannedUser> pageContent = bannedRepository.getAll(
                getAllBannedUserPageReq.getPage(),
                getAllBannedUserPageReq.getSize()
        );

        return BannedUserMapper.getAllBannedUserPage().toResponse(pageContent);
    }
}