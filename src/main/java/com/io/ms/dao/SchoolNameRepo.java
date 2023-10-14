package com.io.ms.dao;

import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolNameResponse2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface SchoolNameRepo extends JpaRepository<SchoolNameRequest, Long> {

    boolean existsByEmail(String email);

    Optional<SchoolNameRequest> findByEmail(String email);


    List<SchoolNameRequest> findByCity(String cities);
}
