package com.io.ms.dao.login;


import com.io.ms.entities.login.CountryMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Serializable> {
}
