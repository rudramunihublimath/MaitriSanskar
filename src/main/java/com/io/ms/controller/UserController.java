package com.io.ms.controller;

import com.io.ms.entities.UserReq;
import com.io.ms.entities.UserLoginReq;
import com.io.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService loginService;

    public UserController(UserService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/MBP/RegisterUser")
    public ResponseEntity<?> registerUser(@RequestBody UserReq payload)  {
        return loginService.registerUser(payload);
    }

    @PostMapping(value = "/MBP/LoginUser")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginReq payload)  {
        return loginService.loginUser(payload);
    }

}
