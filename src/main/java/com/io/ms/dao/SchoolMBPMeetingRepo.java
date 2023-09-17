package com.io.ms.dao;



import com.io.ms.entities.school.SchoolMBPMeetingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface SchoolMBPMeetingRepo extends JpaRepository<SchoolMBPMeetingRequest, Serializable> {
    List<SchoolMBPMeetingRequest> findBySchoolNameRequest_id(Long id);

}
