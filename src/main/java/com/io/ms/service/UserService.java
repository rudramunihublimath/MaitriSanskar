package com.io.ms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.ms.config.JwtService;
import com.io.ms.dao.UserRepository;
import com.io.ms.entities.login.*;
import com.io.ms.token.Token;
import com.io.ms.token.TokenRepository;
import com.io.ms.token.TokenType;
import com.io.ms.utility.AESEncryption;
import com.io.ms.utility.GlobalUtility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    @Autowired
    private JavaMailSender sender;

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
        reg.setCreatedAt(currentDateTime());
        reg.setRole(Role.USER);
        reg.setLinkdinID(payload.getLinkdinID());
        reg.setFacebookID(payload.getFacebookID());
        reg.setInstaID(payload.getInstaID());
        reg.setPannum(payload.getPannum());
        reg.setAddress1(payload.getAddress1());
        reg.setAddress2(payload.getAddress2());
        reg.setPincode(payload.getPincode());
        reg.setProfileActive("Yes");
        var savedUser =userRepo.save(reg);
        var jwtToken = jwtService.generateToken(reg);
        saveUserToken(savedUser, jwtToken);
        return ResponseEntity.ok("Your account has been created. ");
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

        // Create a UserResponse DTO and populate it with user data
        UserResponse resp = createUserResponse(user);

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

    public ResponseEntity<?> forgotPassword(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("Please enter correct mail id!! ", HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(email);
            helper.setText("Login with this details : \n\n"+AESEncryption.decrypt(user.getPassword()));
            helper.setSubject("Email from MaitriSanskar.com");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error while sending mail ", HttpStatus.OK);
        }
        sender.send(message);
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

    private String currentDateTime() {
        // Implement the logic to get the current date and time as a formatted string
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(GlobalUtility.generateDateFormat1());
    }

    private UserResponse createUserResponse(User user) {
        UserResponse resp = new UserResponse();
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
        Role role = user.getRole();
        resp.setRole(role.name());
        resp.setLinkdinID(user.getLinkdinID());
        resp.setFacebookID(user.getFacebookID());
        resp.setInstaID(user.getInstaID());
        resp.setPannum(user.getPannum());
        resp.setAddress1(user.getAddress1());
        resp.setAddress2(user.getAddress2());
        resp.setPincode(user.getPincode());
        resp.setProfileActive(user.getProfileActive());
        resp.setMbpmanagerCode(user.getMbpmanagerCode());
        resp.setMbpmanagerName(user.getMbpmanagerName());
        resp.setNameofTeam(user.getNameofTeam());
        resp.setCitiesAllocated(user.getCitiesAllocated());
        //resp.setAuthorities(user.getAuthorities());

        return resp;
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
}
