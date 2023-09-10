package com.io.ms.dao.login;

import com.io.ms.entities.login.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    //boolean existsByCodeAndEmail(String code, String email);

    Optional<User> findByCodeAndEmail(String code, String email);

    Optional<User> findByCode(String code);
}
