package com.io.ms.dao.login;


import com.io.ms.entities.login.CityMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Serializable> {
    public List<CityMasterEntity> findByStateId(Integer stateId);
}
