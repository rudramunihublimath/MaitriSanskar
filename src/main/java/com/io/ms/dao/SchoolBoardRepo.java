package com.io.ms.dao;


import com.io.ms.entities.school.SchoolBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface SchoolBoardRepo extends JpaRepository<SchoolBoard, Serializable> {

}
