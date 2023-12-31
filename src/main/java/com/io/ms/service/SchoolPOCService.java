package com.io.ms.service;


import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.dao.SchoolPOCRepo;
import com.io.ms.entities.login.User;
import com.io.ms.entities.login.UserReportResp;
import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolPOCRequest;
import com.io.ms.entities.school.SchoolPOCResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolPOCService {
    private static Logger logger = LoggerFactory.getLogger(SchoolPOCService.class);
    @Autowired
    private final SchoolPOCRepo schoolPOCRepo;

    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addSchoolPOC(SchoolPOCRequest payload) {
        Map<String,Object> map = new HashMap<>();

        if(schoolPOCRepo.existsByEmail(payload.getEmail())){
            map.put("message","School POC is already registered with this email");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return ResponseEntity.badRequest().body("School POC is already registered with this email");
        }
        SchoolPOCRequest req= new SchoolPOCRequest();
        req.setTeacherfirstname(payload.getTeacherfirstname());
        req.setTeacherlastname(payload.getTeacherlastname());
        req.setDesignation(payload.getDesignation());
        req.setContactNum1(payload.getContactNum1());
        req.setContactNum2(payload.getContactNum2());
        req.setLinkdinID(payload.getLinkdinID());
        req.setEmail(payload.getEmail());
        req.setFirstContact(payload.getFirstContact());
        req.setSchoolNameRequest(payload.getSchoolNameRequest());
        schoolPOCRepo.save(req);

        map.put("message","School Point of contact added");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok("School Point of contact added ");
    }

    public ResponseEntity<?> editSchoolPOC(SchoolPOCRequest payload) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(payload.schoolNameRequest.getId());
        if (schoolOptional.isEmpty()) {
            map.put("message","School name not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return new ResponseEntity<String>("School name not found !! ", HttpStatus.NOT_FOUND);
        }

        /*SchoolPOCRequest req= new SchoolPOCRequest();
        req.setId(payload.getId());
        req.setTeacherfirstname(payload.getTeacherfirstname());
        req.setTeacherlastname(payload.getTeacherlastname());
        req.setDesignation(payload.getDesignation());
        req.setContactNum1(payload.getContactNum1());
        req.setContactNum2(payload.getContactNum2());
        req.setLinkdinID(payload.getLinkdinID());
        req.setEmail(payload.getEmail());
        req.setFirstContact(payload.getFirstContact()); */
        schoolPOCRepo.save(payload);

        map.put("message","School Point of contact updated ");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok("School Point of contact updated ");
    }

    public ResponseEntity<?> findSchoolPOC(Long id) {
        List<SchoolPOCRequest> poc = schoolPOCRepo.findBySchoolNameRequest_id(id);
        ArrayList<SchoolPOCResponse> resp= new ArrayList<>();
        poc.stream().forEach(i-> resp.add(getSchoolPOCResponse(i)));
        return ResponseEntity.ok(resp);
    }

    private SchoolPOCResponse getSchoolPOCResponse(SchoolPOCRequest req) {
        SchoolPOCResponse resp= new SchoolPOCResponse();
        resp.setId(req.getId());
        resp.setTeacherfirstname(req.getTeacherfirstname());
        resp.setTeacherlastname(req.getTeacherlastname());
        resp.setDesignation(req.getDesignation());
        resp.setContactNum1(req.getContactNum1());
        resp.setContactNum2(req.getContactNum2());
        resp.setLinkdinID(req.getLinkdinID());
        resp.setEmail(req.getEmail());
        resp.setFirstContact(req.getFirstContact());
        return resp;
    }
}
