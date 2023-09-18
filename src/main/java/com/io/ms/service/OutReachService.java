package com.io.ms.service;

import com.io.ms.dao.OutReachRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.login.User;
import com.io.ms.entities.school.OutReachRequest;
import com.io.ms.entities.school.OutReachResponse;
import com.io.ms.entities.school.SchoolNameRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutReachService {
    private static Logger logger = LoggerFactory.getLogger(OutReachService.class);
    @Autowired
    private final OutReachRepo outReachRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addOutReach(OutReachRequest payload,Long schoolId) {
        Optional<SchoolNameRequest> schoolNameRequestOptional = schoolNameRepo.findById(schoolId);

        if (schoolNameRequestOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolNameRequestOptional.get();

            OutReachRequest req = new OutReachRequest();
            req.setOutreachuserid(payload.getOutreachuserid());
            req.setOutreach_assigneddate(payload.getOutreach_assigneddate());
            req.setOutreachheaduserid(payload.getOutreachheaduserid());
            req.setOutreachHead_assigneddate(payload.getOutreachHead_assigneddate());
            req.setOutreach_completed("No");
            req.setNameRequest(schoolNameRequest);
            outReachRepo.save(req);
            return ResponseEntity.ok("OutReach info is added ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> editOutReach(OutReachRequest payload,Long schoolId) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            OutReachRequest req = new OutReachRequest();
            req.setId(payload.getId());
            req.setOutreachuserid(payload.getOutreachuserid());
            req.setOutreach_assigneddate(payload.getOutreach_assigneddate());
            req.setOutreachheaduserid(payload.getOutreachheaduserid());
            req.setOutreachHead_assigneddate(payload.getOutreachHead_assigneddate());
            req.setOutreach_completed(payload.getOutreach_completed());
            req.setNameRequest(schoolNameRequest);
            outReachRepo.save(req);
            return ResponseEntity.ok("OutReach info is Updated ");
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
        OutReachRequest req = schoolOptional.get().getOutReachRequest();

        OutReachResponse resp = new OutReachResponse();
        resp.setId(req.getId());
        resp.setOutreachuserid(req.getOutreachuserid());
        resp.setOutreach_assigneddate(req.getOutreach_assigneddate());
        resp.setOutreachheaduserid(req.getOutreachheaduserid());
        resp.setOutreachHead_assigneddate(req.getOutreachHead_assigneddate());
        resp.setOutreach_completed(req.getOutreach_completed());
        //return new ResponseEntity<>(req, HttpStatus.OK);
        return ResponseEntity.ok(resp);
    }

}
