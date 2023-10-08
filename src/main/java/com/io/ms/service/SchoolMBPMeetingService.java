package com.io.ms.service;


import com.io.ms.dao.SchoolMBPMeetingRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.school.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SchoolMBPMeetingService {
    private static Logger logger = LoggerFactory.getLogger(SchoolMBPMeetingService.class);
    @Autowired
    private final SchoolMBPMeetingRepo schoolMBPMeetingRepo;

    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addSchoolMBPMeeting(SchoolMBPMeetingRequest payload) {
        Map<String,Object> map = new HashMap<>();

        SchoolMBPMeetingRequest req= new SchoolMBPMeetingRequest();
        req.setMeetingDateTime(payload.getMeetingDateTime());
        req.setNextAppointment(payload.getNextAppointment());
        req.setMom( payload.getMom() );
        req.setFeedback_Whatwentwell( payload.getFeedback_Whatwentwell() );
        req.setFeedback_Improvement( payload.getFeedback_Improvement() );
        req.setCreatedDate(payload.getCreatedDate());
        req.setUpdatedDate(payload.getUpdatedDate());
        schoolMBPMeetingRepo.save(payload);

        map.put("message","School Meeting info is added");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok("School Meeting info is added ");
    }

    public ResponseEntity<?> editSchoolMBPMeeting(SchoolMBPMeetingRequest payload) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(payload.schoolNmReq.getId());
        System.out.println("payload id : "+payload.schoolNmReq.getId());
        if (schoolOptional.isEmpty()) {
            map.put("message","School name not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return new ResponseEntity<String>("School name not found !! ", HttpStatus.NOT_FOUND);
        }

        SchoolMBPMeetingRequest req= new SchoolMBPMeetingRequest();
        req.setId(payload.getId());
        req.setMeetingDateTime(payload.getMeetingDateTime());
        req.setNextAppointment(payload.getNextAppointment());
        req.setMom( payload.getMom() );
        req.setFeedback_Whatwentwell( payload.getFeedback_Whatwentwell() );
        req.setFeedback_Improvement( payload.getFeedback_Improvement() );
        req.setCreatedDate(payload.getCreatedDate());
        req.setUpdatedDate(payload.getUpdatedDate());
        schoolMBPMeetingRepo.save(req);

        map.put("message","School Meeting info is updated");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return ResponseEntity.ok("School Meeting info is updated ");
    }

    public ResponseEntity<?> findSchoolMBPMeeting(Long id) {
        List<SchoolMBPMeetingRequest> poc = schoolMBPMeetingRepo.findBySchoolNmReq_id(id);
        ArrayList<SchoolMBPMeetingResponse> resp= new ArrayList<>();
        poc.stream().forEach(i-> resp.add(getSchoolMeetingResponse(i)));
        return ResponseEntity.ok(resp);
    }

    private SchoolMBPMeetingResponse getSchoolMeetingResponse(SchoolMBPMeetingRequest req) {
        SchoolMBPMeetingResponse resp = new SchoolMBPMeetingResponse();
        resp.setId(req.getId());
        resp.setMeetingDateTime(req.getMeetingDateTime());
        resp.setNextAppointment(req.getNextAppointment());
        resp.setMom(req.getMom());
        resp.setFeedback_Whatwentwell(req.getFeedback_Whatwentwell() );
        resp.setFeedback_Improvement(req.getFeedback_Improvement());
        resp.setCreatedDate(req.getCreatedDate());
        resp.setUpdatedDate(req.getUpdatedDate());
        return resp;
    }
}
