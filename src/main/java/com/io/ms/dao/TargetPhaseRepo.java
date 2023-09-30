package com.io.ms.dao;


import com.io.ms.entities.school.TargetPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TargetPhaseRepo extends JpaRepository<TargetPhase, Serializable> {

}
