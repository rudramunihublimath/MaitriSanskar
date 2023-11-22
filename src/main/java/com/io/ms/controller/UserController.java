package com.io.ms.controller;

import com.io.ms.entities.login.ChangePassword;
import com.io.ms.entities.login.User;
import com.io.ms.entities.login.UserLoginReq;
import com.io.ms.exception.UserAppException;
import com.io.ms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PutMapping(value = "/Secured/MBP/Login/ChangePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword payload)  {
        return loginService.changePassword(payload);
    }

    @PutMapping(value = "/Secured/MBP/Login/UpdateUserData")
    public ResponseEntity<?> updateUserDetails(@RequestBody User payload)  {
        return loginService.updateUserDetails(payload);
    }

    @PutMapping(value = "/Secured/MBP/Login/UpdateNOTActive")
    public ResponseEntity<?> updateUserNOTActive(@RequestParam Long userId,
                                                 @RequestParam Long managerId)  {
        return loginService.updateUserNOTActive(userId,managerId);
    }

    @PatchMapping(value="/Secured/MBP/Login/Image",consumes = {"multipart/form-data" })
    @Operation(summary = "Upload a single File")
    public ResponseEntity<?> uploadImage(@RequestParam Long userId,
                                         @RequestParam("file") MultipartFile uploadfile)  {
        return loginService.uploadImage(userId,uploadfile);
    }

    @PatchMapping(value="/Secured/MBP/Login/RemoveImage")
    public ResponseEntity<?> removeImage(@RequestParam Long userId)  {
        return loginService.removeImage(userId);
    }
}
