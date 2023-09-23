package com.io.ms.controller;

import com.io.ms.service.UserReportService;
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
    UserReportService userReportService;

    public UserReportsController(UserReportService userReportService) {
        this.userReportService = userReportService;
    }

    @GetMapping("/Secured/MBP/Login/findByEmail")
    public ResponseEntity<?> search_UserByEmail(@RequestParam String email) {
        return userReportService.search_UserByEmail(email);
    }

    @GetMapping("/Secured/MBP/Login/findUserByMobile")
    public ResponseEntity<?> search_UserByMobile(@RequestParam String contactNum) {
        return userReportService.search_UserByMobile(contactNum);
    }

    @GetMapping("/Secured/MBP/Login/findUserReportingMe")
    public ResponseEntity<?> search_UserReportingMe(@RequestParam Long id) {
        return userReportService.search_UserReportingMe(id);
    }

    @GetMapping("/Secured/MBP/Login/findUserId")
    public ResponseEntity<?> search_findUserId(@RequestParam Long id) {
        return userReportService.search_findUserId(id);
    }

    @GetMapping("/Secured/MBP/Login/countries")
    public Map<Integer, String> getCountries() {
        return userReportService.getCountries();
    }

    @GetMapping("/Secured/MBP/Login/MBPTeam")
    public Map<Integer, String> getMBPTeam() {
        return userReportService.getMBPTeam();
    }

    @GetMapping("/Secured/MBP/Login/states/{countryId}")
    public Map<Integer, String> getStates(@PathVariable Integer countryId) {
        return userReportService.getStates(countryId);
    }

    @GetMapping("/Secured/MBP/Login/cities/{stateId}")
    public Map<Integer, String> getCities(@PathVariable Integer stateId) {
        return userReportService.getCities(stateId);
    }

}
