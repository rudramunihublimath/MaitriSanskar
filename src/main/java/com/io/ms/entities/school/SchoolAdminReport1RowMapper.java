package com.io.ms.entities.school;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SchoolAdminReport1RowMapper implements RowMapper<SchoolAdminReport1> {
    @Override
    public SchoolAdminReport1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        SchoolAdminReport1 ad1= new SchoolAdminReport1();
        ad1.setState(rs.getString(1));
        ad1.setCity(rs.getString(2));
        ad1.setId(rs.getLong(3));
        ad1.setName(rs.getString(4));
        ad1.setContactNum1(rs.getString(5));
        ad1.setAddress1(rs.getString(6));
        ad1.setPincode(rs.getString(7));
        ad1.setEmail(rs.getString(8));

        // Check for null before calling toLocalDate()
        Date createdAtDate = rs.getDate(9);
        ad1.setCreatedAt(createdAtDate != null ? createdAtDate.toLocalDate() : null);

        //ad1.setCreatedAt(rs.getDate(9).toLocalDate());
        ad1.setSpace1(rs.getString(10));
        ad1.setTrainingPartCompleted(rs.getString(11));

        // Check for null before calling toLocalDate()
        Date dateOfCompletionDate = rs.getDate(12);
        ad1.setDateofCompletion(dateOfCompletionDate != null ? dateOfCompletionDate.toLocalDate() : null);

        ad1.setSpace2(rs.getString(13));
        ad1.setDealClosed(rs.getString(14));
        ad1.setDiscontinuedDate(rs.getString(15));
        ad1.setSchoolActive(rs.getString(16));
        ad1.setSchoolInterested(rs.getString(17));
        ad1.setSpace3(rs.getString(18));
        ad1.setAgreementCompleted(rs.getString(19));

        // Check for null before calling toLocalDate()
        Date agreementCompletedDate = rs.getDate(20);
        ad1.setAgreementCompletedDate(agreementCompletedDate != null ? agreementCompletedDate.toLocalDate() : null);

        return ad1;
    }
}
