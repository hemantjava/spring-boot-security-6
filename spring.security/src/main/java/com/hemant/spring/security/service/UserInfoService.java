package com.hemant.spring.security.service;

import com.hemant.spring.security.enity.UserInfo;
import com.hemant.spring.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private  final UserInfoRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserInfo addUserInfo(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return repository.save(userInfo);
    }

    public List<UserInfo> getAllUserInfo(){
        return repository.findAll();
    }

}
