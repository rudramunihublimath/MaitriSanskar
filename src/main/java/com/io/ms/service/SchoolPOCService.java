package com.io.ms.service;


import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.dao.SchoolPOCRepo;
import com.io.ms.entities.login.User;
import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolPOCRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolPOCService {
    private static Logger logger = LoggerFactory.getLogger(SchoolPOCService.class);
    @Autowired
    private final SchoolPOCRepo schoolPOCRepo;

    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addSchoolPOC(SchoolPOCRequest payload) {
        if(schoolPOCRepo.existsByEmail(payload.getEmail())){
            return ResponseEntity.badRequest().body("School POC is already registered with this email");
        }
        SchoolPOCRequest req= new SchoolPOCRequest();
        req.setTeacherfirstname(payload.getTeacherfirstname());
        req.setTeacherlastname(payload.getTeacherlastname());
        req.setDesignation(payload.getDesignation());
        req.setContactNum1(payload.getContactNum1());
        req.setContactNum2(payload.getContactNum2());
        req.setLinkdinID(payload.getLinkdinID());
        req.setEmail(payload.getEmail());
        schoolPOCRepo.save(payload);
        return ResponseEntity.ok("School Point of contact added ");
    }

    public ResponseEntity<?> editSchoolPOC(SchoolPOCRequest payload) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(payload.getId());
        if (schoolOptional.isEmpty()) {
            return new ResponseEntity<String>("School POC info not found !! ", HttpStatus.NOT_FOUND);
        }

        SchoolPOCRequest req= new SchoolPOCRequest();
        req.setId(payload.getId());
        req.setTeacherfirstname(payload.getTeacherfirstname());
        req.setTeacherlastname(payload.getTeacherlastname());
        req.setDesignation(payload.getDesignation());
        req.setContactNum1(payload.getContactNum1());
        req.setContactNum2(payload.getContactNum2());
        req.setLinkdinID(payload.getLinkdinID());
        req.setEmail(payload.getEmail());
        schoolPOCRepo.save(payload);
        return ResponseEntity.ok("School Point of contact updated ");
    }
}