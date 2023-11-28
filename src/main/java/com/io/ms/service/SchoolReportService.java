package com.io.ms.service;

import com.io.ms.entities.school.ResponseMessage;
import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolNameTutorial;
import com.io.ms.utility.CSVHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchoolReportService {
    private static Logger logger = LoggerFactory.getLogger(SchoolReportService.class);
    @Autowired
    private SchoolNameService schoolNameService;

    public ResponseEntity<?> importSchoolListInBulk(MultipartFile file) {
        Map<String,Object> map = new HashMap<>();

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                try {
                    List<SchoolNameTutorial> list = CSVHelper.csvToTutorials(file.getInputStream());

                    for (SchoolNameTutorial schoolNameTutorial : list) {
                        SchoolNameRequest req= new SchoolNameRequest();
                        req.setCountry(schoolNameTutorial.getCountry());
                        req.setState(schoolNameTutorial.getState());
                        req.setCity(schoolNameTutorial.getCity());
                        req.setName(schoolNameTutorial.getName());
                        req.setBoard(Collections.singletonList(schoolNameTutorial.getBoard()));
                        req.setContactNum1(schoolNameTutorial.getContactNum1());
                        req.setContactNum2(schoolNameTutorial.getContactNum2());
                        req.setEmail(schoolNameTutorial.getEmail());
                        req.setLinkdinID(schoolNameTutorial.getLinkdinID());
                        req.setAddress1(schoolNameTutorial.getAddress1());
                        req.setPincode(schoolNameTutorial.getPincode());
                        req.setWebsiteURL(schoolNameTutorial.getWebsiteURL());
                        schoolNameService.registerSchoolName(req);
                    }

                } catch (IOException e) {
                    throw new RuntimeException("fail to store csv data: " + e.getMessage());
                }

                map.put("message","Uploaded the file successfully:"+ file.getOriginalFilename());
                map.put("status",true);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } catch (Exception e) {
                map.put("message","Could not upload the file: " + file.getOriginalFilename() + "!");
                map.put("status",false);
                return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
            }
        }
        else{
            map.put("message","Please upload a csv file!");
            map.put("status",false);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
