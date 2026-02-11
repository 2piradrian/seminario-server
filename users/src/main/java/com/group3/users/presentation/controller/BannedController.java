package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.banneduser.mapper.BannedUserMapper;
import com.group3.users.domain.dto.banneduser.request.BanUserReq;
import com.group3.users.domain.dto.banneduser.request.GetAllBannedUserPageReq;
import com.group3.users.domain.dto.banneduser.response.GetAllBannedUserPageRes;
import com.group3.users.presentation.service.BannedServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/banned")
public class BannedController {

    private final BannedServiceI bannedService;

    @PostMapping("/ban-user")
    public ResponseEntity<Void> banUser(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        BanUserReq banUserReq = BannedUserMapper.banUser().toRequest(token, payload);
        bannedService.banUser(banUserReq);
        return ResponseEntity.ok().build();
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