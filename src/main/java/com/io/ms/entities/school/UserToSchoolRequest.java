package com.io.ms.entities.school;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToSchoolRequest {
    private Long schoolId;
    private Long userId;
    private String teamName;
}
