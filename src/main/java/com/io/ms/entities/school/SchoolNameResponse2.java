package com.io.ms.entities.school;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNameResponse2 {

    private Long id;
    private String name;
    private String email;
    private String city;
    private String contactNum1;
    private String pincode;
    private String address1;
    private Boolean outReachAllocated;
    private Boolean outReachHeadAllocated;
    private Boolean trainingHeadAllocated;

}
