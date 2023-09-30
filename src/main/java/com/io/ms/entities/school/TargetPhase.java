package com.io.ms.entities.school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TargetPhase")
public class TargetPhase {
    @Id
    private Integer Id;

    @Column(name = "name", length = 30)
    private String name;

}
