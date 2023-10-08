package com.io.ms.entities.school;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolGradeResponse {
    private Long id;
    private Integer Year;
    private String gradeName;
    private Integer totalStudentCount;
    private Integer booksGivenCount;
}
