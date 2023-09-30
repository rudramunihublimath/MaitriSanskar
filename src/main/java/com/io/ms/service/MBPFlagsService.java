package com.io.ms.service;


import com.io.ms.dao.MBPFlagsRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.school.MBPFlagsRequest;
import com.io.ms.entities.school.MBPFlagsResponse;
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
public class MBPFlagsService {
    private static Logger logger = LoggerFactory.getLogger(MBPFlagsService.class);
    @Autowired
    private final MBPFlagsRepo mbpFlagsRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addMBPFlagInfo(MBPFlagsRequest payload, Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolNameRequestOptional = schoolNameRepo.findById(schoolId);

        if (schoolNameRequestOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolNameRequestOptional.get();

            MBPFlagsRequest req = new MBPFlagsRequest();
            req.setAgreementCompleted("No");
            req.setAgreementCompletedDate(payload.getAgreementCompletedDate());
            req.setAgreementScanCopyLink(payload.getAgreementScanCopyLink());
            req.setUploadedByUserId(payload.getUploadedByUserId());
            req.setSchoolActive(payload.getSchoolActive());
            req.setSchoolInterested(payload.getSchoolInterested());
            req.setDealClosed("No");
            req.setIsDiscontinued("No");
            req.setDiscontinuedDate(payload.getDiscontinuedDate());
            req.setReasonForDiscontinue(payload.getReasonForDiscontinue());
            req.setReasonValidated(payload.getReasonValidated());
            req.setMbpFlagsReq(schoolNameRequest);
            mbpFlagsRepo.save(req);

            map.put("message","MBPFlags info is added");
            map.put("status",true);
            return new ResponseEntity<>(map, HttpStatus.OK);
            //return ResponseEntity.ok("MBPFlags info is added ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            map.put("message","School with ID " + schoolId + " not found");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> editMBPFlagInfo(MBPFlagsRequest payload,Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            // Check if there is an existing MBPFlagsRequest for the school
            Optional<MBPFlagsRequest> existingMBPFlagsOptional = mbpFlagsRepo.findById(schoolId);
            if (existingMBPFlagsOptional.isPresent()) {
                MBPFlagsRequest existingMBPFlags = existingMBPFlagsOptional.get();

                // Update the properties based on the payload
                //existingMBPFlags.setId(payload.getId());

                Optional<String> agreementCompleted = Optional.ofNullable(payload.getAgreementCompleted());
                agreementCompleted.ifPresent(payloadValue -> {
                    if (payloadValue.equals("Yes")) {
                        // Do something when payloadValue is "Yes"
                        System.out.println("Email Sent to school "+schoolNameRequest.getEmail());
                        existingMBPFlags.setAgreementCompleted("Yes");
                    } else {
                        // Do something when payloadValue is not "Yes"
                        existingMBPFlags.setAgreementCompleted("No");
                    }
                });

                /*
                existingMBPFlags.setAgreementCompletedDate(payload.getAgreementCompletedDate());
                existingMBPFlags.setAgreementScanCopyLink(payload.getAgreementScanCopyLink());
                existingMBPFlags.setUploadedByUserId(payload.getUploadedByUserId());
                existingMBPFlags.setSchoolActive(payload.getSchoolActive());
                existingMBPFlags.setSchoolInterested(payload.getSchoolInterested());  */

                Optional<String>  dealClosed = Optional.ofNullable(payload.getDealClosed());
                dealClosed.ifPresent(payloadValue -> {
                    if (payloadValue.equals("Yes")) {
                        // Do something when payloadValue is "Yes"
                        System.out.println("Email Sent to Training Team ");
                        existingMBPFlags.setDealClosed("Yes");
                    } else {
                        // Do something when payloadValue is not "Yes"
                        existingMBPFlags.setDealClosed("No");
                    }
                });

                /*
                existingMBPFlags.setIsDiscontinued(payload.getIsDiscontinued());
                existingMBPFlags.setDiscontinuedDate(payload.getDiscontinuedDate());
                existingMBPFlags.setReasonForDiscontinue(payload.getReasonForDiscontinue());
                existingMBPFlags.setReasonValidated(payload.getReasonValidated()); */

                existingMBPFlags.setMbpFlagsReq(schoolNameRequest);
                mbpFlagsRepo.save(existingMBPFlags);

                map.put("message","MBPFlags info is Updated");
                map.put("status",true);
                return new ResponseEntity<>(map, HttpStatus.OK);

            }else {
                map.put("message", "MBPFlags data for School with ID " + schoolId + " not found");
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

    public ResponseEntity<?> findMBPFlagInfo(Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isEmpty()) {
            map.put("message","School details not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }
        MBPFlagsRequest req = schoolOptional.get().getMbpFlagsRequest();

        MBPFlagsResponse resp = new MBPFlagsResponse();
        resp.setId(req.getId());
        resp.setAgreementCompleted(req.getAgreementCompleted());
        resp.setAgreementCompletedDate(req.getAgreementCompletedDate());
        resp.setAgreementScanCopyLink(req.getAgreementScanCopyLink());
        resp.setUploadedByUserId(req.getUploadedByUserId());
        resp.setSchoolActive(req.getSchoolActive());
        resp.setSchoolInterested(req.getSchoolInterested());
        resp.setDealClosed(req.getDealClosed());
        resp.setIsDiscontinued(req.getIsDiscontinued());
        resp.setDiscontinuedDate(req.getDiscontinuedDate());
        resp.setReasonForDiscontinue(req.getReasonForDiscontinue());
        resp.setReasonValidated(req.getReasonValidated());

        map.put("message",resp);
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok(resp);
    }

}
