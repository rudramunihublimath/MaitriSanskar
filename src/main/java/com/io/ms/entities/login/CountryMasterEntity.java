package com.io.ms.entities.login;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMasterEntity {
    @Id
    private Integer countryId;
    private Integer countryCode;
    private String countryName;


}
