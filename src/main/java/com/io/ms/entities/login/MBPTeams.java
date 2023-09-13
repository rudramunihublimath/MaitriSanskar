package com.io.ms.entities.login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "MBPTeam")
public class MBPTeams {
    @Id
    private Integer Id;

    @Column(name = "name", length = 50)
    private String name;
}
