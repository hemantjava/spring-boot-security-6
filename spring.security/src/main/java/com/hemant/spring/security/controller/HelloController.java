package com.hemant.spring.security.controller;

import com.hemant.spring.security.enity.UserInfo;
import com.hemant.spring.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HelloController {

    private final UserInfoService userInfoService;


    @GetMapping("normal")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL','ROLE_ADMIN')")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("welcome spring security 3 normal access");
    }

    @GetMapping("admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<String> getAdmin(){
        return ResponseEntity.ok("<h1>welcome spring security 3 admin access</h1>");
    }
    @GetMapping("public")
    public ResponseEntity<String> getPublic(){
        return ResponseEntity.ok("welcome spring security 3 public access");
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserInfo userInfo){
        return ResponseEntity.ok(userInfoService.addUserInfo(userInfo) + ": User added...");
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> getAllUser(@RequestBody UserInfo userInfo){
        return ResponseEntity.ok(userInfoService.getAllUserInfo());
    }
}
