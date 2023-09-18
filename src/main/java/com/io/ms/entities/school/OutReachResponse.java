package com.io.ms.entities.school;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutReachResponse {
    private Long id;
    private String outreachuserid;
    private LocalDate outreach_assigneddate;
    private String outreachheaduserid;
    private LocalDate outreachHead_assigneddate;
    private String outreach_completed;
}
