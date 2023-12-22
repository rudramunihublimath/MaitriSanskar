package com.io.ms.dao;

import com.io.ms.entities.school.SchoolNameRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface SchoolNameRepo extends JpaRepository<SchoolNameRequest, Long> {

    boolean existsByEmail(String email);
    Optional<SchoolNameRequest> findByEmail(String email);
    List<SchoolNameRequest> findByCity(String cities);

    @Modifying
    @Query(value = "INSERT INTO user_School_T (school_id,user_id,user_nameOfTeam) VALUES (?1, ?2 , ?3)", nativeQuery = true)
    @Transactional
    void insertRecord(Long schoolId, Long userId,String teamName);

    @Modifying
    @Query(value = "UPDATE user_School_T SET user_id = ?3 WHERE school_id = ?1 AND user_id = ?2", nativeQuery = true)
    @Transactional
    void updateRecord(Long schoolId, Long userId, Long newUserId);

    @Query(value = "SELECT user_id FROM user_School_T WHERE school_id = :schoolId", nativeQuery = true)
    List<Long> selectRecord(Long schoolId);

    @Query(value = "SELECT user_nameOfTeam FROM user_School_T WHERE school_id = :schoolId", nativeQuery = true)
    List<String> selectRecord2(Long schoolId);

    @Query(value = "select u.email from mbp.users u where u.id in (select ust.user_id from mbp.user_school_t ust where ust.school_id= ?1 ) and u.nameofMyTeam='TrainTheTrainer_Head' ", nativeQuery = true)
    Optional<String> selectTrainingEmailId(Long schoolId);

    @Query(value = "select u.email from mbp.users u where u.id in (select ust.user_id from mbp.user_school_t ust where ust.school_id= ?1 ) and u.nameofMyTeam='OutReach_Head' ", nativeQuery = true)
    Optional<String> selectOutreachHeadEmailId(Long schoolId);

    @Query(value = "select u.email from mbp.users u where u.id in (select ust.user_id from mbp.user_school_t ust where ust.school_id= ?1 ) ", nativeQuery = true)
    List<String> selectAllOutReachEmailId(Long schoolId);
}
