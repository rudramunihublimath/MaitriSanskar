package com.io.ms.utility;

import com.io.ms.entities.login.Role;
import com.io.ms.entities.login.User;
import com.io.ms.entities.login.UserResponse;

public class CommonMethods {

    public static UserResponse createUserResponse(User user) {
        UserResponse resp = new UserResponse();
        resp.setId(user.getId());
        resp.setMbpcode(user.getCode());
        resp.setFirstname(user.getFirstname());
        resp.setLastname(user.getLastname());
        resp.setGender(user.getGender());
        resp.setEmail(user.getEmail());
        user.setPassword("*");
        resp.setContactNum1(user.getContactNum1());
        resp.setContactNum2(user.getContactNum2());
        resp.setCountry(user.getCountry());
        resp.setState(user.getState());
        resp.setCity(user.getCity());
        resp.setDob(user.getDob());
        resp.setCreatedDate(user.getCreatedDate());
        resp.setUpdatedDate(user.getUpdatedDate());
        Role role = user.getRole();
        resp.setRole(role.name());
        resp.setLinkdinID(user.getLinkdinID());
        resp.setFacebookID(user.getFacebookID());
        resp.setInstaID(user.getInstaID());
        resp.setPannum(user.getPannum());
        resp.setAddress1(user.getAddress1());
        //resp.setAddress2(user.getAddress2());
        resp.setPincode(user.getPincode());
        resp.setProfileActive(user.getProfileActive());
        resp.setProfileNOTActiveUpdatedby(user.getProfileNOTActiveUpdatedby());
        resp.setReportingmanagerId(user.getReportingmanagerId());
        resp.setReportingmanagerName(user.getReportingmanagerName());
        resp.setNameofMyTeam(user.getNameofMyTeam());
        resp.setCitiesAllocated(user.getCitiesAllocated());
        //resp.setSchoolAllocated(user.getSchoolAllocated());
        resp.setImageName(user.getImageName());
        return resp;
    }


}
