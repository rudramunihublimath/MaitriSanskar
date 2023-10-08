package com.io.ms.dao;


import com.io.ms.entities.school.SchoolGradeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolGradeRepo extends JpaRepository<SchoolGradeRequest, Long> {
    List<SchoolGradeRequest> findBySchoolNmReq2_id(Long id);
}
