package com.io.ms.entities.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MBPManagerReq {
    String email;
    String mbpmanagerCode;
    String nameofMyTeam;
}
