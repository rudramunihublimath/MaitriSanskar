package com.io.ms.dao;


import com.io.ms.entities.school.AgreementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepo extends JpaRepository<AgreementRequest, Long> {

}
