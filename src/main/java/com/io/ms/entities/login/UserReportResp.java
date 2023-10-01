package com.io.ms.entities.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReportResp {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
