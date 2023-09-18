package com.io.ms.dao;


import com.io.ms.entities.school.OutReachRequest;
import com.io.ms.entities.school.SchoolNameRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutReachRepo extends JpaRepository<OutReachRequest, Long> {

}
