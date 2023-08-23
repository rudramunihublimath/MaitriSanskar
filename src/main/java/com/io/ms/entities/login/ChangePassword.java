package com.io.ms.entities.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePassword {
    String email;
    String oldPWD;
    String newPWD;
}
