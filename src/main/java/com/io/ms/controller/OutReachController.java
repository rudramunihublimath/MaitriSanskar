package com.io.ms.controller;
import com.io.ms.entities.school.OutReachRequest;
import com.io.ms.service.OutReachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OutReachController {
    private static final Logger logger = LoggerFactory.getLogger(OutReachController.class);
    private OutReachService outReachService;

    public OutReachController(OutReachService outReachService) {
        this.outReachService = outReachService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddOutReach")
    public ResponseEntity<?> addOutReach(@RequestBody OutReachRequest payload,@RequestParam Long schoolId)  {
        return outReachService.addOutReach(payload,schoolId);
    }


    @PutMapping(value = "/Secured/MBP/School/EditOutReach")
    public ResponseEntity<?> editOutReach(@RequestBody OutReachRequest payload,@RequestParam Long schoolId)  {
        return outReachService.editOutReach(payload,schoolId);
    }

    @GetMapping(value = "/Secured/MBP/School/FindOutReach")
    public ResponseEntity<?> findOutReach(@RequestParam Long schoolId)  {
        return outReachService.findOutReach(schoolId);
    }




}
