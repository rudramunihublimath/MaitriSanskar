package com.io.ms.controller;

import com.io.ms.entities.login.ChangePassword;
import com.io.ms.entities.login.MBPManagerReq;
import com.io.ms.entities.login.User;
import com.io.ms.entities.login.UserLoginReq;
import com.io.ms.exception.UserAppException;
import com.io.ms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService loginService;

    public UserController(UserService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/MBP/Login/RegisterUser")
    public ResponseEntity<?> registerUser(@RequestBody User payload)  {
        return loginService.registerUser(payload);
    }

    @PostMapping(value = "/MBP/Login/LoginUser")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginReq payload)  {
        return loginService.loginUser(payload);
    }

    @GetMapping("/MBP/Login/forgotPWD")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws UserAppException {
        return loginService.forgotPassword(email);
    }

    @PutMapping(value = "/Secured/MBP/ChangePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword payload)  {
        return loginService.changePassword(payload);
    }

    @PatchMapping(value = "/1Secured/MBP/UpdateUserData")
    public ResponseEntity<?> updateUserDetailsSelf(@RequestBody User payload)  {
        return loginService.updateUserDetailsSelf(payload);
    }

    @PutMapping(value = "/1Secured/MBP/UpdateNOTActive")
    public ResponseEntity<?> updateUserNOTActive(@RequestParam String mbpcode,@RequestParam String email,
                                                 @RequestParam String updatedbyUser)  {
        return loginService.updateUserNOTActive(mbpcode,email,updatedbyUser);
    }

}
