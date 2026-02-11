package com.group3.users.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.users.domain.dto.banneduser.mapper.BannedUserMapper;
import com.group3.users.domain.dto.banneduser.request.BanUserReq;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;
import com.group3.users.domain.dto.user.request.DeleteUserReq;
import com.group3.users.domain.repository.BannedRepositoryI;
import com.group3.users.domain.repository.CatalogRepositoryI;
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
    private final CatalogRepositoryI catalogRepository;
    private final UserService userService;

    @Override
    public void banUser(BanUserReq dto) {
        log.error(dto.toString());
        User adminUser = this.authService.auth(dto.getToken());
        if (adminUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        log.error(adminUser.toString());

        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        ModerationReason reason = this.catalogRepository.getModerationReasonById(dto.getReasonId());
        if (reason == null) throw new ErrorHandler(ErrorType.INVALID_FIELDS);

        log.error(reason.toString());

        User userToBan = this.userRepository.getById(dto.getUserId());
        if (userToBan == null) {
            throw new ErrorHandler(ErrorType.USER_NOT_FOUND);
        }

        log.error(userToBan.toString());

        BannedUser bannedUser = new BannedUser();
        bannedUser.setBannedBy(adminUser);
        bannedUser.setEmail(userToBan.getEmail());
        bannedUser.setReason(reason);
        bannedUser.setCreatedAt(LocalDateTime.now());
        bannedUser.setUpdatedAt(LocalDateTime.now());

        bannedRepository.save(bannedUser);

        this.userService.deleteById(dto.getToken(), dto.getUserId());
    }

    @Override
    public GetAllBannedUserPageRes getAllBannedUsers(GetAllBannedUserPageReq dto) {
        User adminUser = this.authService.auth(dto.getToken());
        if (adminUser == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        if (!adminUser.getRole().equals(Role.ADMIN)) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<BannedUser> pageContent = this.bannedRepository.getAll(
                dto.getPage(),
                dto.getSize()
        );

        for (BannedUser bannedUser : pageContent.getContent()) {
            ModerationReason reason = this.catalogRepository.getModerationReasonById(bannedUser.getReason().getId());
            bannedUser.setReason(reason);
        }

        return BannedUserMapper.getAllBannedUserPage().toResponse(pageContent);
    }
}