package com.io.ms.entities.school;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNameResponse {

    private Long id;
    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private List<String> board;
    private String contactNum1;
    private String contactNum2;
    private List<Long> chainofID;
    private String address1;
    private String address2;
    private String pincode;
    private String websiteURL;
    private String linkdinID;
    private String facebookID;
    private String instaID;
    private String targetPhase;
    private String mbpPersonName;
    private String mbpPersonContactNum;
    private String mbpPersonEmail;
    private String refPersonName;
    private String refPersonContactNum;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
