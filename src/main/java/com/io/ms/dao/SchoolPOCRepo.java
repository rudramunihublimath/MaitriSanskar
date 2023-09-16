package com.io.ms.dao;


import com.io.ms.entities.school.SchoolPOCRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface SchoolPOCRepo extends JpaRepository<SchoolPOCRequest, Serializable> {

    boolean existsByEmail(String email);
}
