package com.hemant.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/")
    public String greet() {
        return "Welcome to spring boot with Oauth2.0";
    }
}
