package com.io.ms.service;


import com.io.ms.dao.SchoolGradeRepo;
import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.entities.school.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolGradeService {
    private static Logger logger = LoggerFactory.getLogger(SchoolGradeService.class);
    @Autowired
    private final SchoolGradeRepo schoolGradeRepo;

    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addSchoolGrades(SchoolGradeRequest payload) {
        Map<String,Object> map = new HashMap<>();

        for(int i=1;i<=8;i++){
            SchoolGradeRequest req = new SchoolGradeRequest();
            req.setYear(LocalDate.now().getYear());
            req.setGradeName("Grade-" + i);
            req.setSchoolNmReq2(payload.getSchoolNmReq2());
            schoolGradeRepo.save(req);
        }

        map.put("message","School Grades are added");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    public ResponseEntity<?> editSchoolGrades(SchoolGradeRequest payload) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(payload.schoolNmReq2.getId());
        if (schoolOptional.isEmpty()) {
            map.put("message","School name not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }
        SchoolGradeRequest req= new SchoolGradeRequest();
        req.setId(payload.getId());
        req.setYear(payload.getYear());
        req.setGradeName(payload.getGradeName());
        req.setTotalStudentCount(payload.getTotalStudentCount());
        req.setBooksGivenCount(payload.getBooksGivenCount());
        req.setSchoolNmReq2(payload.getSchoolNmReq2());
        schoolGradeRepo.save(req);

        map.put("message","School Grades updated ");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<?> findSchoolGrades(Long id) {
        List<SchoolGradeRequest> grade = schoolGradeRepo.findBySchoolNmReq2_id(id);
        ArrayList<SchoolGradeResponse> resp= new ArrayList<>();
        grade.stream().forEach(i-> resp.add(getSchoolGradeResponse(i)));
        return ResponseEntity.ok(resp);
    }

    private SchoolGradeResponse getSchoolGradeResponse(SchoolGradeRequest req) {
        SchoolGradeResponse resp= new SchoolGradeResponse();
        resp.setId(req.getId());
        resp.setYear(req.getYear());
        resp.setGradeName(req.getGradeName());
        resp.setTotalStudentCount(req.getTotalStudentCount());
        resp.setBooksGivenCount(req.getBooksGivenCount());
        return resp;
    }

    public ResponseEntity<?> findAllGradesYear() {
        Map<String,Object> map = new HashMap<>();

        List<SchoolGradeRequest> list = schoolGradeRepo.findAll();
        Set<Integer> respList = list.stream().map(i -> i.getYear()).collect(Collectors.toSet());
        map.put("message",respList);
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
