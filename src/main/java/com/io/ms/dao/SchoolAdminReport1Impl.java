package com.io.ms.dao;

import com.io.ms.entities.school.SchoolAdminReport1;
import com.io.ms.entities.school.SchoolAdminReport1RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchoolAdminReport1Impl  implements SchoolAdminReport1Repo{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SchoolAdminReport1Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<SchoolAdminReport1> findByState(String state) {
        String sql = """
               select a.state,a.city,a.id,a.name,a.contactNum1,a.address1,a.pincode,a.email,a.createdAt,'' as space1,
               b.trainingPartCompleted,b.dateofCompletion,'' as space2,
               c.dealClosed,c.discontinuedDate,c.schoolActive,c.schoolInterested,'' as space3,
               d.agreementCompleted,d.agreementCompletedDate
               from mbp.school_01 a left join mbp.training_04 b on (a.id=b.school_Id)
               left join mbp.mbpflags_05 c on (a.id=c.school_Id)
               left join mbp.agreement_06 d on (a.id=d.school_Id)
               where a.state=?;
               """;
        return jdbcTemplate.query(sql, new SchoolAdminReport1RowMapper(),state);
    }

}
