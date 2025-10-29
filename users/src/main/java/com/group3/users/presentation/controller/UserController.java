package com.group3.users.presentation.controller;

import com.group3.users.domain.dto.user.mapper.UserMapper;
import com.group3.users.domain.dto.user.request.*;
import com.group3.users.presentation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<?> getById(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "userId") String userId
    ) {
        GetUserByIdReq dto = UserMapper.getById().toRequest(token, userId);

        return ResponseEntity.ok(this.userService.getById(dto));
    }

    @GetMapping("/get-all-staff")
    public ResponseEntity<?> getAllStaff(
        @RequestHeader(value = "Authorization") String token
    ) {
        GetAllStaffReq dto = UserMapper.getAllStaff().toRequest(token);

        return ResponseEntity.ok(this.userService.getAllStaff(dto));
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        EditUserReq dto = UserMapper.update().toRequest(token, payload);
        userService.update(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-user-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestBody Map<String, Object> payload
    ) {
        GetUserPageFilteredReq dto = UserMapper.getFiltered().toRequest(payload);
        return ResponseEntity.ok(this.userService.getProfileFiltered(dto));
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
