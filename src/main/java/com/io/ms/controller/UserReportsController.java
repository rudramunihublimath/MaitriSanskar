package com.io.ms.controller;

import com.io.ms.entities.login.ChangePassword;
import com.io.ms.entities.login.MBPManagerReq;
import com.io.ms.entities.login.User;
import com.io.ms.entities.login.UserLoginReq;
import com.io.ms.exception.UserAppException;
import com.io.ms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserReportsController {
    private static final Logger logger = LoggerFactory.getLogger(UserReportsController.class);
    @Autowired
    UserService loginService;

    public UserReportsController(UserService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/Secured/MBP/findByCode")
    public ResponseEntity<?> search_UserByCode(@RequestParam String code) {
        return loginService.search_UserByCode(code);
    }

    @GetMapping("/1Secured/MBP/findByEmail")
    public ResponseEntity<?> search_UserByEmail(@RequestParam String email) {
        return loginService.search_UserByEmail(email);
    }

    @GetMapping("/1Secured/MBP/findUserByMobile")
    public ResponseEntity<?> search_UserByMobile(@RequestParam String contactNum) {
        return loginService.search_UserByMobile(contactNum);
    }

    @GetMapping("/1Secured/MBP/findUserReportingMe")
    public ResponseEntity<?> search_UserReportingMe() {
        return loginService.search_UserReportingMe();
    }

    @GetMapping("/MBP/Login/countries")
    public Map<Integer, String> getCountries() {
        return loginService.getCountries();
    }

    @GetMapping("/MBP/Login/MBPTeam")
    public Map<Integer, String> getMBPTeam() {
        return loginService.getMBPTeam();
    }

    @GetMapping("/MBP/Login/states/{countryId}")
    public Map<Integer, String> getStates(@PathVariable Integer countryId) {
        return loginService.getStates(countryId);
    }

    @GetMapping("/MBP/Login/cities/{stateId}")
    public Map<Integer, String> getCities(@PathVariable Integer stateId) {
        return loginService.getCities(stateId);
    }


}
