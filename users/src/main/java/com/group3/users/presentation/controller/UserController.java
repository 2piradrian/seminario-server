package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.presentation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "userId") String userId
    ) {
        GetUserByIdReq dto = UserMapper.getById().toRequest(userId);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @GetMapping("/get-all-staff")
    public ResponseEntity<?> getOwnProfile(
        @RequestHeader(value = "Authorization") String token
    ) {
        GetAllStaffReq dto = UserMapper.getAllStaff().toRequest(token);

        return ResponseEntity.ok(this.userService.getAllStaff(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
        @RequestHeader(value = "Authorization") String token
    ) {
        DeleteUserReq dto = UserMapper.delete().toRequest(token);
        this.userService.delete(dto);

        return ResponseEntity.ok().build();
    }

}
