package com.io.ms.entities.school;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school_grades_07")
public class SchoolGradeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Year",  length = 10)
    private Integer Year;

    @Column(name = "gradeName", nullable = false, length = 30)
    private String gradeName;

    @Column(name = "TotalStudentCount")
    private Integer totalStudentCount;

    @Column(name = "BooksGivenCount")
    private Integer booksGivenCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schoolNameRequest_id")
    public SchoolNameRequest schoolNmReq2;

}
