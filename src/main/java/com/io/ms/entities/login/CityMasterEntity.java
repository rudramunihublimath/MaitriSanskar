package com.io.ms.entities.login;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "CITIES_MASTER")
public class CityMasterEntity {
    @Id
    private Integer cityId;
    private Integer stateId;
    private String cityName;


}
