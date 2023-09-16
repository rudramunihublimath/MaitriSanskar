package com.io.ms.controller;

import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolPOCRequest;
import com.io.ms.service.SchoolNameService;
import com.io.ms.service.SchoolPOCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchoolPOCController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolPOCController.class);

    SchoolPOCService schoolPOCService;

    public SchoolPOCController(SchoolPOCService schoolPOCService) {
        this.schoolPOCService = schoolPOCService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddSchoolPOC")
    public ResponseEntity<?> addSchoolPOC(@RequestBody SchoolPOCRequest payload)  {
        return schoolPOCService.addSchoolPOC(payload);
    }

    @PutMapping(value = "/Secured/MBP/School/EditSchoolPOC")
    public ResponseEntity<?> editSchoolPOC(@RequestBody SchoolPOCRequest payload)  {
        return schoolPOCService.editSchoolPOC(payload);
    }




}
