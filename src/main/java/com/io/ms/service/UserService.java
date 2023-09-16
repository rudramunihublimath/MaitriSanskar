package com.io.ms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.ms.config.JwtService;
import com.io.ms.constant.AppConstants;
import com.io.ms.dao.*;
import com.io.ms.entities.login.*;
import com.io.ms.exception.UserAppException;
import com.io.ms.properties.AppProperties;
import com.io.ms.token.Token;
import com.io.ms.token.TokenRepository;
import com.io.ms.token.TokenType;
import com.io.ms.utility.AESEncryption;
import com.io.ms.utility.CommonMethods;
import com.io.ms.utility.EmailUtils;
import com.io.ms.utility.GlobalUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final TokenRepository tokenRepository;
    @Autowired
    private AppProperties appProps;
    @Autowired
    private CountryMasterRepo countryRepo;
    @Autowired
    private MBPTeamsRepo mbpTeamsRepo;
    @Autowired
    private StateMasterRepo stateRepo;
    @Autowired
    private CityMasterRepo cityRepo;

    @Autowired
    private EmailUtils emailUtils;

    public ResponseEntity<?> registerUser(User payload) {
        // validated if emailID already present
        if(userRepo.existsByEmail(payload.getEmail())){
            return ResponseEntity.badRequest().body("User is already registered. ");
        }

        User reg = new User();
        reg.setCode(generateUserCode(payload.getCity()) );
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
        reg.setDob(payload.getDob());
        reg.setRole(Role.USER);
        reg.setLinkdinID(payload.getLinkdinID());
        reg.setFacebookID(payload.getFacebookID());
        reg.setInstaID(payload.getInstaID());
        reg.setPannum(payload.getPannum());
        reg.setAddress1(payload.getAddress1());
        reg.setAddress2(payload.getAddress2());
        reg.setPincode(payload.getPincode());
        reg.setProfileActive("Yes");
        if(payload.getNameofMyTeam().equals("Central_Mool")){
            reg.setReportingmanagerId("Admin");
            reg.setReportingmanagerName("Admin");
            reg.setNameofMyTeam(payload.getNameofMyTeam());
            reg.setCitiesAllocated(List.of("N/A"));
        }
        else{
            reg.setReportingmanagerId(payload.getReportingmanagerId());
            String fullname = findUserName(payload.getReportingmanagerId());
            if(fullname.equals("")){
                return ResponseEntity.badRequest().body("Manager Details not found for ID "+payload.getReportingmanagerId());
            }
            reg.setReportingmanagerName(fullname);
            reg.setNameofMyTeam(payload.getNameofMyTeam());
            reg.setCitiesAllocated(payload.getCitiesAllocated());
        }

        var savedUser =userRepo.save(reg);
        var jwtToken = jwtService.generateToken(reg);
        saveUserToken(savedUser, jwtToken);
        return ResponseEntity.ok("Your account has been created");
    }

    public ResponseEntity<?> loginUser(UserLoginReq payload) {
        Optional<User> userOptional = userRepo.findByEmail(payload.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("User not found. Please register !! ", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();

        //validate password
        boolean isPasswordValid = isValidatePassword(payload, user);
        if (!isPasswordValid) {
            return new ResponseEntity<String>("Password is incorrect ", HttpStatus.UNAUTHORIZED);
        }

        UserResponse resp = CommonMethods.createUserResponse(user);

        // Generate JWT and refresh tokens
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        resp.setJwtToken(jwtToken);
        resp.setRefreshToken(refreshToken);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(ChangePassword payload) {
        Optional<User> userOptional = userRepo.findByEmail(payload.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("Please enter correct mail id!! ", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();

        if(AESEncryption.decrypt(user.getPassword()).equals(payload.getOldPWD())) {
            user.setPassword(AESEncryption.encrypt(payload.getNewPWD()));
            userRepo.save(user);
        }
        else{
            return new ResponseEntity<String>("Your Current Password is not correct ", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok("Your Password is changed ");
    }

    public ResponseEntity<?> forgotPassword(String email) throws UserAppException {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("Please enter correct mail id!! ", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();

        String emailBody = readForgotPwdEmailBody(user);
        String subject = appProps.getMessages().get(AppConstants.RECOVER_PAZZWD_EMAIL_SUB);
        try {
            emailUtils.sendEmail(email, subject, emailBody);
        } catch (Exception e) {
            logger.error(AppConstants.EXCEPTION_OCCURRED + e.getMessage(), e);
            throw new UserAppException(e.getMessage());
        }

        return new ResponseEntity<String>( "Please check your mail "+user.getEmail(), HttpStatus.OK);
    }

    private boolean isValidatePassword(UserLoginReq payload, User user) {
        String decryptedString = AESEncryption.decrypt(user.getPassword());
        if(!decryptedString.equals(payload.getPassword()) ) {
            return false;
        }
        return true;
    }

    private String generateUserCode(String city) {
        // Implement your logic to generate the user code
        LocalDateTime currentDateTime = LocalDateTime.now();
        return city.substring(0, 4)+"/"+GlobalUtility.generateSerialNumber()+"/"+currentDateTime.format(GlobalUtility.generateDateFormat2());
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)     // Zero
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepo.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private String readForgotPwdEmailBody(User user) throws UserAppException {
        StringBuilder sb = new StringBuilder(AppConstants.EMPTY_STR);
        String mailBody = AppConstants.EMPTY_STR;
        String fileName = appProps.getMessages().get(AppConstants.RECOVER_PAZZWD_EMAIL_BODY_FILE);
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            String decryptedPwd = AESEncryption.decrypt(user.getPassword());
            mailBody = sb.toString();
            mailBody = mailBody.replace(AppConstants.FNAME, user.getFirstname());
            mailBody = mailBody.replace(AppConstants.LNAME, user.getLastname());
            mailBody = mailBody.replace(AppConstants.PAZZWD, decryptedPwd);
        } catch (Exception e) {
            logger.error(AppConstants.EXCEPTION_OCCURRED + e.getMessage(), e);
            throw new UserAppException(e.getMessage());
        }
        return mailBody;
    }

    public ResponseEntity<?> updateUserDetails(User payload) {
        Optional<User> userOptional = userRepo.findByEmail(payload.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("User not found. Please register !! ", HttpStatus.NOT_FOUND);
        }
        User reg = userOptional.get();

        reg.setFirstname(payload.getFirstname());
        reg.setLastname(payload.getLastname());
        reg.setGender(payload.getGender());
        //reg.setEmail(payload.getEmail());
        //reg.setPassword(AESEncryption.encrypt(payload.getPassword()));
        reg.setContactNum1(payload.getContactNum1());
        reg.setContactNum2(payload.getContactNum2());
        reg.setCountry(payload.getCountry());
        reg.setState(payload.getState());
        reg.setCity(payload.getCity());
        reg.setDob(payload.getDob());
        reg.setLinkdinID(payload.getLinkdinID());
        reg.setFacebookID(payload.getFacebookID());
        reg.setInstaID(payload.getInstaID());
        reg.setPannum(payload.getPannum());
        reg.setAddress1(payload.getAddress1());
        reg.setAddress2(payload.getAddress2());
        reg.setPincode(payload.getPincode());
        reg.setProfileActive("Yes");
        reg.setReportingmanagerId(payload.getReportingmanagerId());
        String fullname = findUserName(payload.getReportingmanagerId());
        if(fullname.equals("")){
            return ResponseEntity.badRequest().body("Manager Details not found for ID "+payload.getReportingmanagerId());
        }
        reg.setReportingmanagerName(fullname);
        reg.setNameofMyTeam(payload.getNameofMyTeam());
        reg.setCitiesAllocated(payload.getCitiesAllocated());
        userRepo.save(reg);
        return ResponseEntity.ok("Your account has been updated");
    }

    public ResponseEntity<?> updateUserNOTActive(String userEmail, String managerEmail) {
        Optional<User> managerOptional = userRepo.findByEmail(managerEmail);
        if (managerOptional.isEmpty()) {
            return new ResponseEntity<String>("Manager details not found !! "+managerEmail, HttpStatus.NOT_FOUND);
        }
        User manager = managerOptional.get();

        Optional<User> userOptional = userRepo.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("User details not found !! "+userEmail, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();

        //validate team precedence
        boolean flag = validateTeamPrecedence(user.getNameofMyTeam(), manager.getNameofMyTeam());
        if(flag==false)
            return new ResponseEntity<String>("Not Authorized !! "+userEmail, HttpStatus.NOT_FOUND);

        user.setProfileActive("No");
        user.setProfileNOTActiveUpdatedby(managerEmail);
        userRepo.save(user);
        return ResponseEntity.ok("Profile is deactivated");
    }

    private boolean validateTeamPrecedence(String uname, String mname) {
        List<MBPTeams> mbpTeams = mbpTeamsRepo.findAll();
        int ux=0,my=0;
        for(MBPTeams mt :mbpTeams){
            if(uname.equals(mt.getName())) {
                ux=mt.getId();
            }
            if(mname.equals(mt.getName())) {
                my=mt.getId();
            }
        }
        if(ux!=0 && my!=0 && my>ux ) return true;

        return false;
    }

    public String findUserName(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "";
        }
        User user = userOptional.get();
        return user.getFirstname()+" "+ user.getLastname();
    }


}
