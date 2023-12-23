package com.io.ms.entities.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolAdminReport1 {
    String state;
    String city;
    Long id;
    String name;
    String contactNum1;
    String address1;
    String pincode;
    String email;
    LocalDate createdAt;
    String space1;
    String trainingPartCompleted;
    LocalDate dateofCompletion;
    String space2;
    String dealClosed;
    String discontinuedDate;
    String schoolActive;
    String schoolInterested;
    String space3;
    String agreementCompleted;
    LocalDate agreementCompletedDate;
    String outReachHead_name;
    String outReachHead_Mob;
    String outReach_name;
    String outReach_Mob;
}
