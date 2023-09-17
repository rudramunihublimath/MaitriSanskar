package com.io.ms.controller;

import com.io.ms.entities.school.SchoolMBPMeetingRequest;
import com.io.ms.entities.school.SchoolPOCRequest;
import com.io.ms.service.SchoolMBPMeetingService;
import com.io.ms.service.SchoolPOCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchoolMBPMeetingController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolMBPMeetingController.class);
    private SchoolMBPMeetingService schoolMBPMeetingService;

    public SchoolMBPMeetingController(SchoolMBPMeetingService schoolMBPMeetingService) {
        this.schoolMBPMeetingService = schoolMBPMeetingService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddSchoolMBPMeeting")
    public ResponseEntity<?> addSchoolMBPMeeting(@RequestBody SchoolMBPMeetingRequest payload)  {
        return schoolMBPMeetingService.addSchoolMBPMeeting(payload);
    }

    @PutMapping(value = "/Secured/MBP/School/EditSchoolMBPMeeting")
    public ResponseEntity<?> editSchoolMBPMeeting(@RequestBody SchoolMBPMeetingRequest payload)  {
        return schoolMBPMeetingService.editSchoolMBPMeeting(payload);
    }

    @GetMapping(value = "/Secured/MBP/School/FindSchoolMBPMeeting")
    public ResponseEntity<?> findSchoolMBPMeeting(@RequestParam Long id)  {
        return schoolMBPMeetingService.findSchoolMBPMeeting(id);
    }
    
}
