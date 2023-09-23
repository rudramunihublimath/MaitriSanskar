package com.io.ms.controller;

import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.service.SchoolNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchoolNameController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolNameController.class);

    SchoolNameService schoolNameService;

    public SchoolNameController(SchoolNameService schoolNameService) {
        this.schoolNameService = schoolNameService;
    }

    @PostMapping(value = "/Secured/MBP/School/RegisterSchoolName")
    public ResponseEntity<?> registerSchoolName(@RequestBody SchoolNameRequest payload)  {
        return schoolNameService.registerSchoolName(payload);
    }

    @GetMapping(value = "/Secured/MBP/School/FindSchoolById")
    public ResponseEntity<?> findSchoolById(@RequestParam Long id)  {
        return schoolNameService.findSchoolById(id);
    }

    @PatchMapping(value = "/Secured/MBP/School/EditSchoolInfo")
    public ResponseEntity<?> editSchoolInfo(@RequestBody SchoolNameRequest payload)  {
        return schoolNameService.editSchoolInfo(payload);
    }




}
