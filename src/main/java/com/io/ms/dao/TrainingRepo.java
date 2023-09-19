package com.io.ms.dao;


import com.io.ms.entities.school.TrainingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepo extends JpaRepository<TrainingRequest, Long> {

}
