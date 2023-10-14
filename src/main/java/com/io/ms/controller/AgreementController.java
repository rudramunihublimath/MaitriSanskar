package com.io.ms.controller;



import com.io.ms.entities.school.AgreementRequest;
import com.io.ms.exception.UserAppException;
import com.io.ms.service.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AgreementController {
    private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);
    private AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddAgreementInfo")
    public ResponseEntity<?> addAgreementInfo(@RequestBody AgreementRequest payload, @RequestParam Long schoolId)  {
        return agreementService.addAgreementInfo(payload,schoolId);
    }


    @PutMapping(value = "/Secured/MBP/School/EditAgreementInfo")
    public ResponseEntity<?> editAgreementInfo(@RequestBody AgreementRequest payload,@RequestParam Long schoolId) throws UserAppException {
        return agreementService.editAgreementInfo(payload,schoolId);
    }

    @GetMapping(value = "/Secured/MBP/School/FindAgreementInfo")
    public ResponseEntity<?> findAgreementInfo(@RequestParam Long schoolId)  {
        return agreementService.findAgreementInfo(schoolId);
    }

}
