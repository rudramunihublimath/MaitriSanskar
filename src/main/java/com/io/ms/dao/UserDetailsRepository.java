package com.io.ms.dao;

import com.io.ms.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    @Query(value="SELECT * FROM mbp.user_details WHERE code = :code", nativeQuery=true)
    UserDetails findByCode(String code);
}
