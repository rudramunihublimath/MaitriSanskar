package com.io.ms.controller;

import com.io.ms.service.SchoolReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchoolReportController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolReportController.class);

    private SchoolReportService schoolReportService;

    public SchoolReportController(SchoolReportService schoolReportService) {
        this.schoolReportService = schoolReportService;
    }

    @PostMapping(value = "/Secured/MBP/School/ImportSchoolListInBulk",consumes = {"multipart/form-data" })
    public ResponseEntity<?> importSchoolListInBulk(@RequestParam("file") MultipartFile file)  {
        return schoolReportService.importSchoolListInBulk(file);
    }

    @GetMapping("/Secured/MBP/School/Admin/Report1")
    public void generateReport1(HttpServletResponse response,
                                             @RequestParam String state) throws IOException {
        schoolReportService.generateReport1(response,state);
    }
}
