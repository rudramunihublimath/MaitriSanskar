package com.io.ms.dao;


import com.io.ms.entities.UserLoginResponse;
import com.io.ms.entities.UserReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<UserReq, Long> {
    UserReq findByEmail(String email);

}
