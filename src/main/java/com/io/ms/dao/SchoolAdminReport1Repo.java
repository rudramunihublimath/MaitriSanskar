package com.io.ms.dao;

import com.io.ms.entities.school.SchoolAdminReport1;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface SchoolAdminReport1Repo {
    List<SchoolAdminReport1> findByState(String state);
}
