package com.io.ms.dao;


import com.io.ms.entities.school.AgreementRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgreementRepo extends JpaRepository<AgreementRequest, Long> {

    @Query(value = "select a.* from mbp.agreement_06 a where a.school_id= ?1 ", nativeQuery = true)
    Optional<AgreementRequest> findBySchool_Id(Long schoolId);
}
