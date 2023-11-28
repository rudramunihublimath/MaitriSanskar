package com.io.ms.dao;


import com.io.ms.entities.school.MBPFlagsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MBPFlagsRepo extends JpaRepository<MBPFlagsRequest, Long> {

    @Query(value = "select a.* from mbp.MBPFlags a where a.school_id= ?1 ", nativeQuery = true)
    Optional<MBPFlagsRequest> findBySchool_Id(Long schoolId);
}
