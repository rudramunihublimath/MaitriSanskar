package com.io.ms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
@Table(name = "user_details")
@Data
public class UserDetails { //implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mbpmanagerCode", length = 21)
    private String mbpmanagerCode;

    @Column(name = "nameofTeam", length = 50)
    private String nameofTeam;

    //@Column(name = "listofcities", columnDefinition = "JSON")
    //private List<String> listofcities;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code", referencedColumnName = "code", nullable = false)
    private UserReq user;

}
