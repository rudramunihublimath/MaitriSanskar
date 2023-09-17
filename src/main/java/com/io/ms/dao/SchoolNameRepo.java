package com.io.ms.dao;

import com.io.ms.entities.school.SchoolNameRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface SchoolNameRepo extends JpaRepository<SchoolNameRequest, Serializable> {

    boolean existsByEmail(String email);

    Optional<SchoolNameRequest> findByEmail(String email);


}
