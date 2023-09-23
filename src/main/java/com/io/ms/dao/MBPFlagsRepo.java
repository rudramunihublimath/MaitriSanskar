package com.io.ms.dao;


import com.io.ms.entities.school.MBPFlagsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MBPFlagsRepo extends JpaRepository<MBPFlagsRequest, Long> {

}
