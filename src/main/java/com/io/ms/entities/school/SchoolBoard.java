package com.io.ms.entities.school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "SchoolBoard")
public class SchoolBoard {
    @Id
    private Integer Id;

    @Column(name = "name", length = 15)
    private String name;

}
