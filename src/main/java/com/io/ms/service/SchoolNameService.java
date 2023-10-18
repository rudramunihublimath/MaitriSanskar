package com.io.ms.service;

import com.io.ms.dao.*;
import com.io.ms.entities.login.MBPTeams;
import com.io.ms.entities.login.UserReportResp;
import com.io.ms.entities.school.*;
import com.io.ms.utility.GlobalUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolNameService {
    private static Logger logger = LoggerFactory.getLogger(SchoolNameService.class);
    @Autowired
    private final SchoolNameRepo schoolNameRepo;
    @Autowired
    private final SchoolBoardRepo boardRepo;

    @Autowired
    private final TargetPhaseRepo targetPhaseRepo;

    @Autowired
    private final SchoolPOCRepo schoolPOCRepo;
    @Autowired
    private final SchoolMBPMeetingRepo schoolMBPMeetingRepo;
    @Autowired
    private final OutReachRepo outReachRepo;
    @Autowired
    private final TrainingRepo trainingRepo;
    @Autowired
    private final MBPFlagsRepo mbpFlagsRepo;

    public ResponseEntity<?> registerSchoolName(SchoolNameRequest payload) {
        Map<String,Object> map = new HashMap<>();

        // validated if emailID already present
        if(schoolNameRepo.existsByEmail(payload.getEmail())){
            map.put("message","School is already registered with this email");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }

        SchoolNameRequest sc=new SchoolNameRequest();
        sc.setName(payload.getName());
        sc.setCode(generateCode(payload.getName()));
        //sc.setPassword("");
        sc.setEmail(payload.getEmail());
        sc.setCountry(payload.getCountry());
        sc.setState(payload.getState());
        sc.setCity(payload.getCity());
        sc.setBoard(payload.getBoard());
        sc.setContactNum1(payload.getContactNum1());
        sc.setContactNum2(payload.getContactNum2());
        sc.setChainofID(payload.getChainofID());
        sc.setAddress1(payload.getAddress1());
        sc.setAddress2(payload.getAddress2());
        sc.setPincode(payload.getPincode());
        sc.setWebsiteURL(payload.getWebsiteURL());
        sc.setLinkdinID(payload.getLinkdinID());
        sc.setFacebookID(payload.getFacebookID());
        sc.setInstaID(payload.getInstaID());
        sc.setTargetPhase(payload.getTargetPhase());
        sc.setMbpPersonName(payload.getMbpPersonName());
        sc.setMbpPersonContactNum(payload.getMbpPersonContactNum());
        sc.setMbpPersonEmail(payload.getMbpPersonEmail());
        sc.setRefPersonName(payload.getRefPersonName());
        sc.setRefPersonContactNum(payload.getRefPersonContactNum());
        schoolNameRepo.save(sc);
        map.put("message","School is registered");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private String generateCode(String schoolName) {
        //LocalDateTime currentDateTime = LocalDateTime.now();
        //String strFormat = currentDateTime.format(GlobalUtility.generateDateFormat2());
        return schoolName.substring(0,1)+ GlobalUtility.generateSerialNumber();
    }

    public ResponseEntity<?> findSchoolById(Long id) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(id);
        if (schoolOptional.isEmpty()) {
            map.put("message","School details not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
            //return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }
        SchoolNameRequest req = schoolOptional.get();
        SchoolNameResponse sc= new SchoolNameResponse();
        sc.setId(req.getId());
        sc.setName(req.getName());
        sc.setCode(req.getCode());
        sc.setEmail(req.getEmail());
        sc.setCountry(req.getCountry());
        sc.setState(req.getState());
        sc.setCity(req.getCity());
        sc.setBoard(req.getBoard());
        sc.setContactNum1(req.getContactNum1());
        sc.setContactNum2(req.getContactNum2());
        sc.setChainofID(req.getChainofID());
        sc.setAddress1(req.getAddress1());
        sc.setAddress2(req.getAddress2());
        sc.setPincode(req.getPincode());
        sc.setWebsiteURL(req.getWebsiteURL());
        sc.setLinkdinID(req.getLinkdinID());
        sc.setFacebookID(req.getFacebookID());
        sc.setInstaID(req.getInstaID());
        sc.setTargetPhase(req.getTargetPhase());
        sc.setMbpPersonName(req.getMbpPersonName());
        sc.setMbpPersonContactNum(req.getMbpPersonContactNum());
        sc.setMbpPersonEmail(req.getMbpPersonEmail());
        sc.setRefPersonName(req.getRefPersonName());
        sc.setRefPersonContactNum(req.getRefPersonContactNum());
        sc.setCreatedDate(req.getCreatedDate());
        sc.setUpdatedDate(req.getUpdatedDate());
        map.put("message",sc);
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
        //return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    public ResponseEntity<?> editSchoolInfo(Long schoolId,SchoolNameRequest payload) {
        Map<String,Object> map = new HashMap<>();

        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isEmpty()) {
            map.put("message","School details not found !!");
            map.put("status",false);
            return ResponseEntity.badRequest().body(map);
        }

        SchoolNameRequest sc = schoolOptional.get();
        //sc.setName(payload.getName());
        sc.setEmail(payload.getEmail());
        sc.setCity(payload.getCity());
        sc.setBoard(payload.getBoard());
        sc.setContactNum1(payload.getContactNum1());
        sc.setContactNum2(payload.getContactNum2());
        sc.setChainofID(payload.getChainofID());
        sc.setAddress1(payload.getAddress1());
        sc.setAddress2(payload.getAddress2());
        sc.setPincode(payload.getPincode());
        sc.setWebsiteURL(payload.getWebsiteURL());
        sc.setLinkdinID(payload.getLinkdinID());
        sc.setFacebookID(payload.getFacebookID());
        sc.setInstaID(payload.getInstaID());
        //sc.setTargetPhase(payload.getTargetPhase());
        sc.setMbpPersonName(payload.getMbpPersonName());
        sc.setMbpPersonContactNum(payload.getMbpPersonContactNum());
        sc.setMbpPersonEmail(payload.getMbpPersonEmail());
        sc.setRefPersonName(payload.getRefPersonName());
        sc.setRefPersonContactNum(payload.getRefPersonContactNum());
        schoolNameRepo.save(sc);
        map.put("message","School Information is updated ");
        map.put("status",true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public Map<Integer, String> getSchoolBoard() {
        List<SchoolBoard> board = boardRepo.findAll();
        Map<Integer, String> TeamMap = new HashMap<>();
        board.forEach(i -> TeamMap.put(i.getId(), i.getName()));
        return TeamMap;
    }

    public Map<Integer, String> getTargetPhase() {
        List<TargetPhase> tPhase = targetPhaseRepo.findAll();
        Map<Integer, String> TeamMap = new HashMap<>();
        tPhase.forEach(i -> TeamMap.put(i.getId(), i.getName()));
        return TeamMap;
    }


    public ResponseEntity<?> findAllSchoolInCity(String cities) {
        String[] cityArray = cities.split(",");

        List<SchoolNameRequest> schoolLists = Arrays.stream(cityArray)
                .parallel()
                .map(myCity -> schoolNameRepo.findByCity(myCity))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<SchoolNameResponse2> resp = schoolLists.parallelStream()
                .map(this::getSchoolListResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resp);
    }

    private SchoolNameResponse2 getSchoolListResponse(SchoolNameRequest req) {
        SchoolNameResponse2 resp= new SchoolNameResponse2();
        resp.setId(req.getId());
        resp.setName(req.getName());
        resp.setEmail(req.getEmail());
        resp.setCity(req.getCity());
        resp.setContactNum1(req.getContactNum1());
        resp.setPincode(req.getPincode());
        return resp;
    }


    public ResponseEntity<?> findAllSchoolForGivenCityndSchoolName(String schoolId) {
        String[] schId = schoolId.split(",");

        List<Long> schIdList = new ArrayList<>();
        for (String id : schId) {
            schIdList.add(Long.parseLong(id.trim()));
        }

        List<SchoolNameRequest> list = schoolNameRepo.findAllById(schIdList);

        List<SchoolNameResponse2> resp = list.parallelStream()
                .map(this::getSchoolListResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resp);
    }


}
