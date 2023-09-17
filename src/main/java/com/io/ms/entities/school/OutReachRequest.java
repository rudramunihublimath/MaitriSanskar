package com.io.ms.entities.school;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "outreach")
public class OutReachRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "outreach_userid", nullable = false, length = 50)
    private String outreach_userid;

    @Column(name = "outreach_assigneddate", nullable = false, length = 30)
    private LocalDate outreach_assigneddate;

    @Column(name = "outreachHead_userid", nullable = false, length = 50)
    private String outreachHead_userid;

    @Column(name = "outreachHead_uassigneddate", nullable = false, length = 30)
    private LocalDate outreachHead_assigneddate;

    @Column(name = "outreach_completed", nullable = false, length = 5)
    private String outreach_completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolNameRequest_id")
    public SchoolNameRequest schoolNameRequest;

}
