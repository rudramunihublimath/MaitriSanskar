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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MBPFlagsService {
    private static Logger logger = LoggerFactory.getLogger(MBPFlagsService.class);
    @Autowired
    private final MBPFlagsRepo mbpFlagsRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addOutReach(MBPFlagsRequest payload, Long schoolId) {
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
            return ResponseEntity.ok("MBPFlags info is added ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> editOutReach(MBPFlagsRequest payload,Long schoolId) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            MBPFlagsRequest req = new MBPFlagsRequest();
            req.setId(payload.getId());


            req.setMbpFlagsReq(schoolNameRequest);
            mbpFlagsRepo.save(req);
            return ResponseEntity.ok("MBPFlags info is Updated ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> findOutReach(Long schoolId) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isEmpty()) {
            return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }
        MBPFlagsRequest req = schoolOptional.get().getMbpFlagsRequest();

        MBPFlagsResponse resp = new MBPFlagsResponse();
        resp.setId(req.getId());

        return ResponseEntity.ok(resp);
    }

}
