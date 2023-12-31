package com.io.ms.service;

import com.io.ms.config.JwtService;
import com.io.ms.dao.*;
import com.io.ms.entities.login.*;
import com.io.ms.properties.AppProperties;
import com.io.ms.token.TokenRepository;
import com.io.ms.utility.CommonMethods;
import com.io.ms.utility.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserReportService {
    private static Logger logger = LoggerFactory.getLogger(UserReportService.class);
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

    public Map<Integer, String> getCountries() {
        List<CountryMasterEntity> countries = countryRepo.findAll();

        Map<Integer, String> countryMap = new HashMap<>();
        countries.forEach(country -> countryMap.put(country.getCountryId(), country.getCountryName()));
        return countryMap;

    }

    public Map<Integer, String> getStates(Integer countryId) {
        List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);

        Map<Integer, String> stateMap = new HashMap<>();
        states.forEach(state -> stateMap.put(state.getStateId(), state.getStateName()));
        return stateMap;
    }

    public Map<Integer, String> getCities(Integer stateId) {
        List<CityMasterEntity> cities = cityRepo.findByStateId(stateId);

        Map<Integer, String> cityMap = new HashMap<>();
        cities.forEach(city -> cityMap.put(city.getCityId(), city.getCityName()));
        return cityMap;
    }

    /*
    public ResponseEntity<?> search_UserByCode(String code) {
        Optional<User> userOptional = userRepo.findByCode(code);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<String>("User not found with given details !! "+code, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        UserReportResp resp = getUserReportResponse(user);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    } */

    public ResponseEntity<?> search_UserByEmail(String email) {
        Map<String,Object> map = new HashMap<>();

        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            map.put("message","User not found with given details");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return new ResponseEntity<String>("User not found with given details !! "+email, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        UserResponse resp = CommonMethods.createUserResponse(user);
        map.put("message",resp);
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<?> search_UserByMobile(String contactNum) {
        List<User> user = userRepo.findByContactNumContainingSubstring(contactNum);
        ArrayList<UserReportResp> resp= new ArrayList<>();
        user.stream().forEach(i-> resp.add(getUserReportResponse(i)));
        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<?> search_UserReportingMe(Long id) {
        List<User> user = userRepo.findByReportingmanagerId(id);
        ArrayList<UserReportResp> resp= new ArrayList<>();
        user.stream().forEach(i-> resp.add(getUserReportResponse(i)));
        return ResponseEntity.ok(resp);
    }

    private UserReportResp getUserReportResponse(User user) {
        UserReportResp resp = new UserReportResp();
        resp.setId(user.getId());
        resp.setFirstname(user.getFirstname());
        resp.setLastname(user.getLastname());
        resp.setEmail(user.getEmail());
        resp.setContactNum1(user.getContactNum1());
        resp.setNameofMyTeam(user.getNameofMyTeam());
        return resp;
    }

    public Map<Integer, String> getMBPTeam() {
        List<MBPTeams> mbpTeams = mbpTeamsRepo.findAll();

        Map<Integer, String> TeamMap = new HashMap<>();
        mbpTeams.forEach(i -> TeamMap.put(i.getId(), i.getName()));
        return TeamMap;
    }

    public ResponseEntity<?> search_findUserId(Long id) {
        Map<String,Object> map = new HashMap<>();

        Optional<User> userOptional= userRepo.findById(id);
        if (userOptional.isEmpty()) {
            map.put("message","User not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }
        User user = userOptional.get();
        UserResponse resp = CommonMethods.createUserResponse(user);

        map.put("message",resp);
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<?> searchBook(String nameofMyTeam, String name) {
        Map<String,Object> map = new HashMap<>();

        List<User> user = userRepo.findByNameofMyTeam(nameofMyTeam,name);

        ArrayList<UserReportResp> resp= new ArrayList<>();
        user.stream().forEach(i-> resp.add(getUserReportResponse(i)));
        return ResponseEntity.ok(resp);
    }


}
