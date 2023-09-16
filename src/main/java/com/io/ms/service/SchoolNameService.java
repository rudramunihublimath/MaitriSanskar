package com.io.ms.service;

import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolNameService {
    private static Logger logger = LoggerFactory.getLogger(SchoolNameService.class);
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> registerSchoolName(SchoolNameRequest payload) {
        // validated if emailID already present
        if(schoolNameRepo.existsByEmail(payload.getEmail())){
            return ResponseEntity.badRequest().body("School is already registered with this email");
        }

        SchoolNameRequest sc=new SchoolNameRequest();
        sc.setName(payload.getName());
        sc.setEmail(payload.getEmail());
        sc.setCity(payload.getCity());
        sc.setBoard(payload.getBoard());
        sc.setContactNum1(payload.getContactNum1());
        sc.setContactNum2(payload.getContactNum2());
        sc.setChainofID(payload.getChainofID());
        sc.setAddress1(payload.getAddress1());
        sc.setAddress2(payload.getAddress2());
        sc.setPincode(payload.getPincode());
        sc.setWebsiteURL(payload.getWebsiteURL());
        sc.setLinkdinID(payload.getLinkdinID());
        sc.setFacebookID(payload.getFacebookID());
        sc.setInstaID(payload.getInstaID());
        sc.setTargetPhase(payload.getTargetPhase());
        sc.setMbpPersonName(payload.getMbpPersonName());
        sc.setMbpPersonContactNum(payload.getMbpPersonContactNum());
        sc.setMbpPersonEmail(payload.getMbpPersonEmail());
        sc.setRefPersonName(payload.getRefPersonName());
        sc.setRefPersonContactNum(payload.getRefPersonContactNum());
        schoolNameRepo.save(sc);
        return ResponseEntity.ok("School is registered");
    }

    public ResponseEntity<?> findSchoolByEmail(String email) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findByEmail(email);
        if (schoolOptional.isEmpty()) {
            return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }
        SchoolNameRequest req = schoolOptional.get();
        SchoolNameResponse sc= new SchoolNameResponse();
        sc.setId(req.getId());
        sc.setName(req.getName());
        sc.setEmail(req.getEmail());
        sc.setCity(req.getCity());
        sc.setBoard(req.getBoard());
        sc.setContactNum1(req.getContactNum1());
        sc.setContactNum2(req.getContactNum2());
        sc.setChainofID(req.getChainofID());
        sc.setAddress1(req.getAddress1());
        sc.setAddress2(req.getAddress2());
        sc.setPincode(req.getPincode());
        sc.setWebsiteURL(req.getWebsiteURL());
        sc.setLinkdinID(req.getLinkdinID());
        sc.setFacebookID(req.getFacebookID());
        sc.setInstaID(req.getInstaID());
        sc.setTargetPhase(req.getTargetPhase());
        sc.setMbpPersonName(req.getMbpPersonName());
        sc.setMbpPersonContactNum(req.getMbpPersonContactNum());
        sc.setMbpPersonEmail(req.getMbpPersonEmail());
        sc.setRefPersonName(req.getRefPersonName());
        sc.setRefPersonContactNum(req.getRefPersonContactNum());
        sc.setCreatedDate(req.getCreatedDate());
        sc.setUpdatedDate(req.getUpdatedDate());
        return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    public ResponseEntity<?> editSchoolInfo(SchoolNameRequest payload) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findByEmail(payload.getEmail());
        if (schoolOptional.isEmpty()) {
            return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }

        SchoolNameRequest sc = schoolOptional.get();
        //sc.setName(payload.getName());
        //sc.setEmail(payload.getEmail());
        //sc.setCity(payload.getCity());
        sc.setBoard(payload.getBoard());
        sc.setContactNum1(payload.getContactNum1());
        sc.setContactNum2(payload.getContactNum2());
        sc.setChainofID(payload.getChainofID());
        sc.setAddress1(payload.getAddress1());
        sc.setAddress2(payload.getAddress2());
        sc.setPincode(payload.getPincode());
        sc.setWebsiteURL(payload.getWebsiteURL());
        sc.setLinkdinID(payload.getLinkdinID());
        sc.setFacebookID(payload.getFacebookID());
        sc.setInstaID(payload.getInstaID());
        //sc.setTargetPhase(payload.getTargetPhase());
        sc.setMbpPersonName(payload.getMbpPersonName());
        sc.setMbpPersonContactNum(payload.getMbpPersonContactNum());
        sc.setMbpPersonEmail(payload.getMbpPersonEmail());
        sc.setRefPersonName(payload.getRefPersonName());
        sc.setRefPersonContactNum(payload.getRefPersonContactNum());
        schoolNameRepo.save(sc);
        return ResponseEntity.ok("School Information is updated ");
    }
}
