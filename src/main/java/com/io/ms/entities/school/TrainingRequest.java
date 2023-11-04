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
@Table(name = "training")
public class TrainingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "trainTheTrainersId", nullable = false, length = 50)
    //private String trainTheTrainersId;

    //@Column(name = "trainTheTrainerHeadId", nullable = false, length = 50)
    //private String trainTheTrainerHeadId;

    @Column(name = "trainingPartCompleted",nullable = true, length = 5)
    private String trainingPartCompleted;

    @Column(name = "dateofCompletion", nullable = true, length = 30)
    private LocalDate dateofCompletion;

    @Column(name = "dataValidated", nullable = true,length = 5)
    private String dataValidated;

    @OneToOne
    @JoinColumn(name = "school_Id")
    private SchoolNameRequest trainRequest;


}
