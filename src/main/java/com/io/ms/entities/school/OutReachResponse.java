package com.io.ms.entities.school;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutReachResponse {
    private Long id;
    private String teacherfirstname;
    private String teacherlastname;
    private String designation;
    private String contactNum1;
    private String contactNum2;
    private String linkdinID;
    private String email;
}
