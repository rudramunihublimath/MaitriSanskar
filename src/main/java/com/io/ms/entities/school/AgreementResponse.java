package com.io.ms.entities.school;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreementResponse {
    private Long id;
    private String agreementCompleted;
    private LocalDate agreementCompletedDate;
    private String agreementScanCopyLink;
    private Long uploadedByUserId;
}
