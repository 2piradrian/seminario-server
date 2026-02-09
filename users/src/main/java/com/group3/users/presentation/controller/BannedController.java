package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.banneduser.mapper.BannedUserMapper;
import com.group3.users.domain.dto.banneduser.request.BanUserReq;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;
import com.group3.users.presentation.service.BannedServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banned")
@AllArgsConstructor
public class BannedController {

    private final BannedServiceI bannedService;

    @PostMapping("/ban-user")
    public ResponseEntity<Void> banUser(
            @RequestHeader("Authorization") String token,
            @RequestParam String userId,
            @RequestParam String reason
    ) {
        BanUserReq banUserReq = BannedUserMapper.banUser().toRequest(token, userId, reason);
        bannedService.banUser(banUserReq);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-all-banned-user-page")
    public ResponseEntity<GetAllBannedUserPageRes> getAllBannedUsers(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size
    ) {
        GetAllBannedUserPageReq getAllBannedUserPageReq = BannedUserMapper.getAllBannedUserPage().toRequest(token, page, size);
        return ResponseEntity.ok(bannedService.getAllBannedUsers(getAllBannedUserPageReq));
    }
}