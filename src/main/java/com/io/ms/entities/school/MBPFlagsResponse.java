package com.io.ms.entities.school;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MBPFlagsResponse {
    private Long id;
    private String agreementCompleted;
    private LocalDate agreementCompletedDate;
    private String agreementScanCopyLink;
    private String uploadedByUserId;
    private String schoolActive;
    private String schoolInterested;
    private String dealClosed;
    private String isDiscontinued;
    private LocalDate discontinuedDate;
    private String reasonForDiscontinue;
    private String reasonValidated;
}
