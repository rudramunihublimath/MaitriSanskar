package com.io.ms.entities.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNameTutorial {
    private String country;
    private String state;
    private String city;
    private String name;
    private String board;
    private String contactNum1;
    private String contactNum2;
    private String email;
    private String  linkdinID;
    private String address1;
    private String pincode;
    private String websiteURL;
    private String teacherfirstname;
    private String teacherlastname;
    private String designation;
}
