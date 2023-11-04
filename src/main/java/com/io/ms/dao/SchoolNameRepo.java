package com.io.ms.dao;

import com.io.ms.entities.school.SchoolNameRequest;
import com.io.ms.entities.school.SchoolNameResponse2;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query(value = "INSERT INTO user_School_T (school_id,user_id) VALUES (?1, ?2)", nativeQuery = true)
    @Transactional
    void insertRecord(Long schoolId, Long userId);

    @Modifying
    @Query(value = "UPDATE user_School_T SET user_id = ?3 WHERE school_id = ?1 AND user_id = ?2", nativeQuery = true)
    @Transactional
    void updateRecord(Long schoolId, Long userId, Long newUserId);

    @Query(value = "SELECT user_id FROM user_School_T WHERE school_id = :schoolId", nativeQuery = true)
    List<Long> selectRecord(Long schoolId);
}
