package com.io.ms.dao;

import com.io.ms.entities.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<User> findByCodeAndEmail(String code, String email);

    Optional<User> findByCode(String code);

    @Query("SELECT u FROM User u WHERE u.contactNum1 LIKE %:contactNum% OR u.contactNum2 LIKE %:contactNum%")
    List<User> findByContactNumContainingSubstring(@Param("contactNum") String contactNum);

    List<User> findByReportingmanagerId(Long id);
}
