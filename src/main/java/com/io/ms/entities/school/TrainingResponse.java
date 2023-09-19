package com.io.ms.entities.school;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResponse {
    private Long id;
    private String trainTheTrainersId;
    private String trainTheTrainerHeadId;
    private String trainingPartCompleted;
    private LocalDate dateofCompletion;
    private String dataValidated;
}
