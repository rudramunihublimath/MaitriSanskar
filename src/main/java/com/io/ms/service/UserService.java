package com.io.ms.service;

import com.io.ms.dao.UserDetailsRepository;
import com.io.ms.dao.UserRepository;
import com.io.ms.entities.login.*;
import com.io.ms.utility.AESEncryption;
import com.io.ms.utility.GlobalUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserDetailsRepository detailsRepo;

    @Autowired
    private JavaMailSender sender;

    public UserService(UserRepository userRepo, UserDetailsRepository detailsRepo) {
        this.userRepo = userRepo;
        this.detailsRepo = detailsRepo;
    }

    public ResponseEntity<?> registerUser(UserReq payload) {
        // validated if emailID already present
        UserReq userLogin = userRepo.findByEmail(payload.getEmail());
        if (userLogin != null)
            return new ResponseEntity<String>("User is already present in our record", HttpStatus.BAD_REQUEST);
        else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String code=payload.getCity().substring(0, 4)+"/"+GlobalUtility.generateSerialNumber()+"/"+currentDateTime.format(GlobalUtility.generateDateFormat2());

            UserDetails userDetails = new UserDetails();
            UserReq reg = new UserReq();
            reg.setCode(code);
            reg.setFirstname(payload.getFirstname());
            reg.setLastname(payload.getLastname());
            reg.setGender(payload.getGender());
            reg.setEmail(payload.getEmail());
            reg.setPassword(AESEncryption.encrypt(payload.getPassword()));
            reg.setContactNum1(payload.getContactNum1());
            reg.setContactNum2(payload.getContactNum2());
            reg.setCountry(payload.getCountry());
            reg.setState(payload.getState());
            reg.setCity(payload.getCity());
            reg.setCreatedAt(currentDateTime.format(GlobalUtility.generateDateFormat1()));
            reg.setDbRole("Regular");
            reg.setLinkdinID(payload.getLinkdinID());
            reg.setFacebookID(payload.getFacebookID());
            reg.setInstaID(payload.getInstaID());
            reg.setPannum(payload.getPannum());
            reg.setAddress1(payload.getAddress1());
            reg.setAddress2(payload.getAddress2());
            reg.setPincode(payload.getPincode());
            reg.setProfileActive("Yes");

            // Set child reference(userProfile) in parent entity(user)
            reg.setUserDetails(userDetails);
            // Set parent reference(user) in child entity(userProfile)
            userDetails.setUser(reg);
            userRepo.save(reg);
        }
        return new ResponseEntity<String>("Your account is created " , HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(UserLoginReq payload) {
        UserReq user = userRepo.findByEmail(payload.getEmail());

        if (user == null || user.getEmail().length() <= 0){
            return new ResponseEntity<String>("Please register!!", HttpStatus.OK);
        }
        if (user != null ){
            String decryptedString = AESEncryption.decrypt(user.getPassword());
            if( !decryptedString.equals(payload.getPassword()) ) {
                return new ResponseEntity<String>("Password is incorrect", HttpStatus.OK);
            }
        }
        UserDetails userDetails = detailsRepo.findByCode(user.getCode());
        UserLoginResponse resp = new UserLoginResponse();
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
        resp.setCreatedAt(user.getCreatedAt());
        resp.setUpdatedAt(user.getUpdatedAt());
        resp.setDbRole(user.getDbRole());
        resp.setLinkdinID(user.getLinkdinID());
        resp.setFacebookID(user.getFacebookID());
        resp.setInstaID(user.getInstaID());
        resp.setPannum(user.getPannum());
        resp.setAddress1(user.getAddress1());
        resp.setAddress2(user.getAddress2());
        resp.setPincode(user.getPincode());
        resp.setProfileActive(user.getProfileActive());
        resp.setToken("123123123");
        resp.setMbpmanagerCode(userDetails.getMbpmanagerCode());
        resp.setNameofTeam(userDetails.getNameofTeam());
        return new ResponseEntity<>(resp , HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(ChangePassword payload) {
        UserReq user = userRepo.findByEmail(payload.getEmail());
        if (user == null){
            return new ResponseEntity<String>("Please enter correct mail id!!", HttpStatus.OK);
        }

        if(AESEncryption.decrypt(user.getPassword()).equals(payload.getOldPWD())) {
            user.setPassword(AESEncryption.encrypt(payload.getNewPWD()));
            userRepo.save(user);
        }
        else{
            return new ResponseEntity<String>("Your Current Password is not correct ", HttpStatus.OK);
        }

        return new ResponseEntity<String>( "Your Password is changed ", HttpStatus.OK);
    }

    public ResponseEntity<?> forgotPassword(String email) {
        UserReq user = userRepo.findByEmail(email);
        if (user == null){
            return new ResponseEntity<String>("Please enter correct mail id!!", HttpStatus.OK);
        }
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setText("\n\n"+AESEncryption.decrypt(user.getPassword()));
            helper.setSubject("Email from MaitriSanskar.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error while sending mail", HttpStatus.OK);
        }
        sender.send(message);
        return new ResponseEntity<String>( "Please check your mail for PWD : "+user.getEmail(), HttpStatus.OK);
    }
}
