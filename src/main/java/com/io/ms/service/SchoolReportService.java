package com.io.ms.service;

import com.io.ms.constant.AppConstants;
import com.io.ms.dao.SchoolAdminReport1Impl;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.dao.SchoolPOCRepo;
import com.io.ms.dao.UserRepository;
import com.io.ms.entities.login.User;
import com.io.ms.entities.school.SchoolAdminReport1;
import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolNameTutorial;
import com.io.ms.entities.school.SchoolPOCRequest;
import com.io.ms.utility.CSVHelper;
import com.io.ms.utility.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SchoolReportService {
    private static Logger logger = LoggerFactory.getLogger(SchoolReportService.class);
    @Autowired
    private SchoolNameService schoolNameService;
    @Autowired
    private SchoolAdminReport1Impl schoolAdminReport1;
    @Autowired
    private SchoolPOCService schoolPOCService;
    @Autowired
    private SchoolNameRepo schoolNameRepo;
    @Autowired
    private SchoolPOCRepo schoolPOCRepo;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> importSchoolListInBulk(MultipartFile file) {
        Map<String,Object> map = new HashMap<>();

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                try {
                    List<SchoolNameTutorial> list = CSVHelper.csvToTutorials(file.getInputStream());
                    //int i=1;
                    for (SchoolNameTutorial schoolNameTutorial : list) {
                        //System.out.println(i);i++;
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
                        SchoolNameRequest schRequest = schoolNameService.registerSchoolName_V2(req);

                        SchoolPOCRequest req2 = new SchoolPOCRequest();
                        req2.setTeacherfirstname(schoolNameTutorial.getTeacherfirstname());
                        req2.setTeacherlastname(schoolNameTutorial.getTeacherlastname());
                        req2.setDesignation(schoolNameTutorial.getDesignation());
                        req2.setContactNum1(null);
                        req2.setContactNum2(null);
                        req2.setLinkdinID(null);
                        req2.setEmail(null);
                        req2.setFirstContact(null);
                        req2.setSchoolNameRequest(schRequest);

                        schoolPOCRepo.save(req2);
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

    public void generateReport1(HttpServletResponse response, String state) throws IOException {
        Map<String,Object> map = new HashMap<>();

        List<SchoolAdminReport1> list = schoolAdminReport1.findByState(state); // main code

        list.forEach(item -> {
            List<Long> userIds = schoolNameRepo.selectRecord_OutReach(item.getId());
            userIds.stream()
                    .map(userRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(user -> {
                        if (user.getNameofMyTeam().equals(AppConstants.OutReach_Head)) {
                            item.setOutReachHead_name(user.getFirstname() + " " + user.getLastname());
                            item.setOutReachHead_Mob(user.getContactNum1());
                        } else if (user.getNameofMyTeam().equals(AppConstants.OutReach)) {
                            item.setOutReach_name(user.getFirstname() + " " + user.getLastname());
                            item.setOutReach_Mob(user.getContactNum1());
                        }
                    });
        });

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SchoolAdminReport_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelGenerator generator = new ExcelGenerator(list);
        generator.generateExcelFile(response);

        //map.put("message","Report1 generated successfully");
        //map.put("message2",list);
        //map.put("status",true);
        //return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
