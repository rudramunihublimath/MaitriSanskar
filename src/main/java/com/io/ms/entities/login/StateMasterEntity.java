package com.io.ms.entities.login;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "STATES_MASTER")
public class StateMasterEntity {
    @Id
    private Integer stateId;
    private Integer countryId;
    private String stateName;


}
