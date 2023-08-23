package com.io.ms.entities.login;

import lombok.Data;

@Data
public class UserLoginResponse {

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

    private String createdAt;

    private String updatedAt;

    private String dbRole;

    private String linkdinID;

    private String facebookID;

    private String instaID;

    private String pannum;

    private String address1;

    private String address2;

    private String pincode;

    private String profileActive;

    private String token;

    private String mbpmanagerCode;

    private String nameofTeam;
}
