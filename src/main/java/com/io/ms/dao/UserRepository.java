package com.io.ms.dao;


import com.io.ms.entities.login.UserReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserReq, Long> {
    UserReq findByEmail(String email);

}
