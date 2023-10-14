package com.io.ms.controller;


import com.io.ms.entities.school.SchoolGradeRequest;
import com.io.ms.entities.school.SchoolMBPMeetingRequest;
import com.io.ms.service.SchoolGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SchoolGradeController {
    private static final Logger logger = LoggerFactory.getLogger(SchoolGradeController.class);

    SchoolGradeService schoolGradeService;

    public SchoolGradeController(SchoolGradeService schoolGradeService) {
        this.schoolGradeService = schoolGradeService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddSchoolGrades")
    public ResponseEntity<?> addSchoolGrades(@RequestBody SchoolGradeRequest payload)  {
        return schoolGradeService.addSchoolGrades(payload);
    }

    @PutMapping(value = "/Secured/MBP/School/EditSchoolGrades")
    public ResponseEntity<?> editSchoolGrades(@RequestBody SchoolGradeRequest payload)  {
        return schoolGradeService.editSchoolGrades(payload);
    }

    @GetMapping(value = "/Secured/MBP/School/FindSchoolGrades")
    public ResponseEntity<?> findSchoolGrades(@RequestParam Long id)  {
        return schoolGradeService.findSchoolGrades(id);
    }

    @GetMapping(value = "/Secured/MBP/School/FindAllGradesYear")
    public ResponseEntity<?> findAllGradesYear()  {
        return schoolGradeService.findAllGradesYear();
    }

}
