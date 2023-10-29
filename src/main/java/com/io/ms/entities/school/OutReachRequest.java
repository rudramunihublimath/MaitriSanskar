package com.io.ms.entities.school;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "outreach")
public class OutReachRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "outreachuserid", nullable = false, length = 50)
    private String outreachuserid;

    @Column(name = "outreach_assigneddate", nullable = true, length = 30)
    private LocalDate outreach_assigneddate;

    @Column(name = "outreachheaduserid", nullable = false, length = 50)
    private String outreachheaduserid;

    @Column(name = "outreachHead_uassigneddate", nullable = true, length = 30)
    private LocalDate outreachHead_assigneddate;

    @Column(name = "outreach_completed", length = 5)
    private String outreach_completed;

    @OneToOne
    @JoinColumn(name = "school_Id")
    private SchoolNameRequest nameRequest;


}
