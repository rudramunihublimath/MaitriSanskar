package com.io.ms.service;


import com.io.ms.constant.AppConstants;
import com.io.ms.dao.MBPFlagsRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.dao.TrainingRepo;
import com.io.ms.entities.school.*;
import com.io.ms.exception.UserAppException;
import com.io.ms.properties.AppProperties;
import com.io.ms.utility.AESEncryption;
import com.io.ms.utility.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private final TrainingRepo trainingRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;
    @Autowired
    private AppProperties appProps;
    @Autowired
    private EmailUtils emailUtils;

    public ResponseEntity<?> addMBPFlagInfo(MBPFlagsRequest payload, Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolNameRequestOptional = schoolNameRepo.findById(schoolId);

        if (schoolNameRequestOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolNameRequestOptional.get();

            MBPFlagsRequest req = new MBPFlagsRequest();
            /*req.setAgreementCompleted("No");
            req.setAgreementCompletedDate(payload.getAgreementCompletedDate());
            req.setAgreementScanCopyLink(payload.getAgreementScanCopyLink());
            req.setUploadedByUserId(payload.getUploadedByUserId()); */
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

    public ResponseEntity<?> editMBPFlagInfo(MBPFlagsRequest payload,Long schoolId)  throws UserAppException{
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            // Check if there is an existing MBPFlagsRequest for the school
            Optional<MBPFlagsRequest> existingMBPFlagsOptional = mbpFlagsRepo.findById(schoolId);
            if (existingMBPFlagsOptional.isPresent()) {
                MBPFlagsRequest req = existingMBPFlagsOptional.get();

                req.setSchoolActive(payload.getSchoolActive());
                req.setSchoolInterested(payload.getSchoolInterested());

                if (payload.getDealClosed().equals("Yes")) {
                    // Do something when payloadValue is "Yes"
                    Optional<TrainingRequest> trOptional = trainingRepo.findById(schoolId);
                    if (trOptional.isPresent()) {
                        TrainingRequest trainingRequest = trOptional.get();
                        String email="rudra.hublimath@gmail.com";
                        String emailBody = readTrainingEmailBody(schoolNameRequest);
                        String subject = appProps.getMessages().get(AppConstants.TRAINING_EMAIL_SUB);
                        try {
                            emailUtils.sendEmail(email, subject, emailBody);
                        } catch (Exception e) {
                            logger.error(AppConstants.EXCEPTION_OCCURRED + e.getMessage(), e);
                            throw new UserAppException(e.getMessage());
                        }
                        System.out.println("Email Sent to Training Team Head ");
                        req.setDealClosed("Yes");
                    }
                    else{
                        map.put("message","Training email ID not found");
                        map.put("status",false);
                        return new ResponseEntity<>(map, HttpStatus.OK);
                    }
                } else {
                    req.setDealClosed("No");
                }

                req.setIsDiscontinued(payload.getIsDiscontinued());
                req.setDiscontinuedDate(payload.getDiscontinuedDate());
                req.setReasonForDiscontinue(payload.getReasonForDiscontinue());
                req.setReasonValidated(payload.getReasonValidated());

                req.setMbpFlagsReq(schoolNameRequest);
                mbpFlagsRepo.save(req);

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

    private String readTrainingEmailBody(SchoolNameRequest req) throws UserAppException {
        StringBuilder sb = new StringBuilder(AppConstants.EMPTY_STR);
        String mailBody = AppConstants.EMPTY_STR;
        String fileName = appProps.getMessages().get(AppConstants.TRAINING_EMAIL_BODY_FILE);
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            mailBody = sb.toString();
            mailBody = mailBody.replace(AppConstants.SCHOOL_ID,String.valueOf(req.getId()));
            mailBody = mailBody.replace(AppConstants.SCHOOL_NAME, req.getName());
        } catch (Exception e) {
            logger.error(AppConstants.EXCEPTION_OCCURRED + e.getMessage(), e);
            throw new UserAppException(e.getMessage());
        }
        return mailBody;
    }

    public ResponseEntity<?> findMBPFlagInfo(Long schoolId) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest school = schoolOptional.get();
            MBPFlagsRequest req = school.getMbpFlagsRequest();

            if (req!=null) {
                MBPFlagsResponse resp = new MBPFlagsResponse();
                resp.setId(req.getId());
                /*resp.setAgreementCompleted(req.getAgreementCompleted());
                resp.setAgreementCompletedDate(req.getAgreementCompletedDate());
                resp.setAgreementScanCopyLink(req.getAgreementScanCopyLink());
                resp.setUploadedByUserId(req.getUploadedByUserId()); */
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
            }
            else {
                map.put("message", "MBPFlags data for School with ID " + schoolId + " not found");
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
