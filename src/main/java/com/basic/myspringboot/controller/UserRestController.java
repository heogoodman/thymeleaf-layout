package com.basic.myspringboot.controller;

import com.basic.myspringboot.service.UserService;
import com.basic.myspringboot.dto.UserReqDto;
import com.basic.myspringboot.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public UserResDto saveUser(@RequestBody UserReqDto userReqDto) {
        return userService.saveUser(userReqDto);
    }

    @GetMapping("/{myId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public UserResDto getUserById(@PathVariable("myId") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserResDto> getUser() {
        return userService.getUsers();
    }

    @PatchMapping("/{email}")
    public UserResDto upateUser(@PathVariable String email,
                                @RequestBody UserReqDto userReqDto){
        return userService.updateUser(email, userReqDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id + " User가 삭제처리 되었습니다.");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

}
