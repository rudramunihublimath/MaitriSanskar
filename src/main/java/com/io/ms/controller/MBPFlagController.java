package com.io.ms.controller;

import com.io.ms.entities.school.MBPFlagsRequest;
import com.io.ms.service.MBPFlagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MBPFlagController {
    private static final Logger logger = LoggerFactory.getLogger(MBPFlagController.class);
    private MBPFlagsService mbpFlagsService;

    public MBPFlagController(MBPFlagsService mbpFlagsService) {
        this.mbpFlagsService = mbpFlagsService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddMBPFlagInfo")
    public ResponseEntity<?> addMBPFlagInfo(@RequestBody MBPFlagsRequest payload, @RequestParam Long schoolId)  {
        return mbpFlagsService.addMBPFlagInfo(payload,schoolId);
    }


    @PutMapping(value = "/Secured/MBP/School/EditMBPFlagInfo")
    public ResponseEntity<?> editMBPFlagInfo(@RequestBody MBPFlagsRequest payload,@RequestParam Long schoolId)  {
        return mbpFlagsService.editMBPFlagInfo(payload,schoolId);
    }

    @GetMapping(value = "/Secured/MBP/School/FindMBPFlagInfo")
    public ResponseEntity<?> findMBPFlagInfo(@RequestParam Long schoolId)  {
        return mbpFlagsService.findMBPFlagInfo(schoolId);
    }




}
