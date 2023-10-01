package com.io.ms.service;


import com.io.ms.dao.AgreementRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.school.AgreementRequest;
import com.io.ms.entities.school.AgreementResponse;
import com.io.ms.entities.school.SchoolNameRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgreementService {
    private static Logger logger = LoggerFactory.getLogger(AgreementService.class);
    @Autowired
    private final AgreementRepo agreementRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addAgreementInfo(AgreementRequest payload, Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolNameRequestOptional = schoolNameRepo.findById(schoolId);

        if (schoolNameRequestOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolNameRequestOptional.get();

            AgreementRequest req = new AgreementRequest();
            req.setAgreementCompleted("No");
            req.setAgreementCompletedDate(payload.getAgreementCompletedDate());
            req.setAgreementScanCopyLink(payload.getAgreementScanCopyLink());
            req.setUploadedByUserId(payload.getUploadedByUserId());

            req.setAgreementReq(schoolNameRequest);
            agreementRepo.save(req);

            map.put("message","Agreement info is added");
            map.put("status",true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            map.put("message","School with ID " + schoolId + " not found");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    public ResponseEntity<?> editAgreementInfo(AgreementRequest payload,Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            // Check if there is an existing MBPFlagsRequest for the school
            Optional<AgreementRequest> agreementRequestOptional = agreementRepo.findById(schoolId);
            if (agreementRequestOptional.isPresent()) {
                AgreementRequest req = agreementRequestOptional.get();

                if (payload.getAgreementCompleted().equals("Yes")) {
                    // Do something when payloadValue is "Yes"
                    System.out.println("Email Sent to School "+schoolNameRequest.getEmail());
                    req.setAgreementCompleted("Yes");
                } else {
                    req.setAgreementCompleted("No");
                }

                req.setAgreementCompletedDate(payload.getAgreementCompletedDate());
                req.setAgreementScanCopyLink(payload.getAgreementScanCopyLink());
                req.setUploadedByUserId(payload.getUploadedByUserId());

                req.setAgreementReq(schoolNameRequest);
                agreementRepo.save(req);

                map.put("message","MBPFlags info is Updated");
                map.put("status",true);
                return new ResponseEntity<>(map, HttpStatus.OK);

            }else {
                map.put("message", "Agreement data for School with ID " + schoolId + " not found");
                map.put("status", false);
                return ResponseEntity.badRequest().body(map);
            }
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            map.put("message","School with ID " + schoolId + " not found");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }
    }

    public ResponseEntity<?> findAgreementInfo(Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest school = schoolOptional.get();
            AgreementRequest req = school.getAgreementRequest();

            if (req!=null) {
                AgreementResponse resp = new AgreementResponse();
                resp.setId(req.getId());
                resp.setAgreementCompleted(req.getAgreementCompleted());
                resp.setAgreementCompletedDate(req.getAgreementCompletedDate());
                resp.setAgreementScanCopyLink(req.getAgreementScanCopyLink());
                resp.setUploadedByUserId(req.getUploadedByUserId());

                map.put("message",resp);
                map.put("status",true);
                return new ResponseEntity<>(map, HttpStatus.OK);
            }
            else {
                map.put("message", "Agreement data for School with ID " + schoolId + " not found");
                map.put("status", false);
                return ResponseEntity.badRequest().body(map);
            }
        }
        else {
            map.put("message","School with ID " + schoolId + " not found");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }
    }

}
