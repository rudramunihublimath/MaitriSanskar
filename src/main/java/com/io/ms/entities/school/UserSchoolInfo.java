package com.io.ms.entities.school;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchoolInfo {
    private String firstname;
    private String lastname;
    private String email;
    private String contactNum1;
    private String contactNum2;
    private String nameofMyTeam;
}
