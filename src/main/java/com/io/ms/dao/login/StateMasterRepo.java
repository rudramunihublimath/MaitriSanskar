package com.io.ms.dao.login;


import com.io.ms.entities.login.StateMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface StateMasterRepo extends JpaRepository<StateMasterEntity, Serializable> {
    public List<StateMasterEntity> findByCountryId(Integer countryId);
}
