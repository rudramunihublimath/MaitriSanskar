package com.io.ms.dao;


import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolPOCRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;


public interface SchoolPOCRepo extends JpaRepository<SchoolPOCRequest, Serializable> {

    boolean existsByEmail(String email);

    List<SchoolPOCRequest> findBySchoolNameRequest_id(Long id);

}
