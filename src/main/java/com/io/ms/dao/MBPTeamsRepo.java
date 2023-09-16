package com.io.ms.dao;


import com.io.ms.entities.login.MBPTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface MBPTeamsRepo extends JpaRepository<MBPTeams, Serializable> {

}
