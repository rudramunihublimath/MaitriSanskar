package com.io.ms.entities.school;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mbpflags")
public class MBPFlagsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schoolActive", nullable = true,length = 5)
    private String schoolActive;

    @Column(name = "schoolInterested", nullable = true,length = 5)
    private String schoolInterested;

    @Column(name = "dealClosed", nullable = true,length = 5)
    private String dealClosed;

    @Column(name = "isDiscontinued", nullable = true,length = 5)
    private String isDiscontinued;

    @Column(name = "discontinuedDate", nullable = true, length = 30)
    private LocalDate discontinuedDate;

    @Column(name = "reasonForDiscontinue", nullable = true, length = 100)
    private String reasonForDiscontinue;

    @Column(name = "reasonValidated", nullable = true, length = 5)
    private String reasonValidated;

    @OneToOne
    @JoinColumn(name = "school_Id")
    private SchoolNameRequest mbpFlagsReq;

}
