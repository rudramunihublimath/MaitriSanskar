package com.io.ms.entities.login;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
public class UserResponse {

    private String mbpcode;
    private String firstname;
    private String lastname;
    private Gender gender;
    private String email;
    private String password;
    private String contactNum1;
    private String contactNum2;
    private String country;
    private String state;
    private String city;
    private LocalDate dob;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private String role;
    private String linkdinID;
    private String facebookID;
    private String instaID;
    private String pannum;
    private String address1;
    private String address2;
    private String pincode;
    private String profileActive;
    private String profileNOTActiveUpdatedby;
    private String jwtToken;
    private String refreshToken;
    private String reportingmanagerId;
    private String reportingmanagerName;
    private String nameofMyTeam;
    private List<String> citiesAllocated;
}
