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
@Table(name = "agreement")
public class AgreementRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agreementCompleted", nullable = true,length = 50)
    private String agreementCompleted;

    @Column(name = "agreementCompletedDate", nullable = true, length = 30)
    private LocalDate agreementCompletedDate;

    @Column(name = "agreementScanCopyLink", nullable = true, length = 300)
    private String agreementScanCopyLink;

    @Column(name = "uploadedByUserId", nullable = true,length = 10)
    private Long uploadedByUserId;

    @OneToOne
    @JoinColumn(name = "school_Id")
    private SchoolNameRequest agreementReq;

}
