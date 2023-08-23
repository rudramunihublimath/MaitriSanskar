package com.io.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq { //implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 25)
    private String code;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Email
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "contactnum1", nullable = false, length = 15)
    private String contactNum1;

    @Column(name = "contactnum2", nullable = false, length = 15)
    private String contactNum2;

    @Column(name = "country", nullable = false, length = 15)
    private String country;

    @Column(name = "state", nullable = false, length = 20)
    private String state;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdat")
    private String createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updatedat")
    private String updatedAt;

    @Column(name = "dbrole", length = 15)
    private String dbRole;

    @Column(name = "linkdinid", length = 100)
    private String linkdinID;

    @Column(name = "facebookid", length = 100)
    private String facebookID;

    @Column(name = "instaid", length = 100)
    private String instaID;

    @Column(name = "pannum", nullable = false, length = 15)
    private String pannum;

    @Column(name = "address1", nullable = false, length = 60)
    private String address1;

    @Column(name = "address2", length = 60)
    private String address2;

    @Column(name = "pincode", nullable = false,length = 12)
    private String pincode;

    @Column(name = "profileactive", length = 3)
    private String profileActive;


    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetails userDetails;



}