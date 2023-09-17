package com.io.ms.entities.school;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolMBPMeetingResponse {
    private Long id;
    private LocalDateTime meetingDateTime;
    private LocalDateTime nextAppointment;
    private String mom;
    private String feedback_Whatwentwell;
    private String feedback_Improvement;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
