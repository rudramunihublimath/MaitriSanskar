package com.io.ms.service;

import com.io.ms.dao.SchoolNameRepo;
import com.io.ms.dao.TrainingRepo;
import com.io.ms.entities.school.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private static Logger logger = LoggerFactory.getLogger(TrainingService.class);
    @Autowired
    private final TrainingRepo trainingRepo;
    @Autowired
    private final SchoolNameRepo schoolNameRepo;

    public ResponseEntity<?> addTraining(TrainingRequest payload, Long schoolId) {
        Optional<SchoolNameRequest> schoolNameRequestOptional = schoolNameRepo.findById(schoolId);

        if (schoolNameRequestOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolNameRequestOptional.get();

            TrainingRequest req = new TrainingRequest();
            req.setTrainTheTrainersId(payload.getTrainTheTrainersId());
            req.setTrainTheTrainerHeadId(payload.getTrainTheTrainerHeadId());
            req.setTrainingPartCompleted("No");
            //req.setDateofCompletion(payload.getDateofCompletion());
            req.setDataValidated("No");
            req.setTrainRequest(schoolNameRequest);
            trainingRepo.save(req);
            return ResponseEntity.ok("Training info is added ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> editTraining(TrainingRequest payload,Long schoolId) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isPresent()) {
            SchoolNameRequest schoolNameRequest = schoolOptional.get();

            TrainingRequest req = new TrainingRequest();
            req.setId(payload.getId());
            req.setTrainTheTrainersId(payload.getTrainTheTrainersId());
            req.setTrainTheTrainerHeadId(payload.getTrainTheTrainerHeadId());
            req.setTrainingPartCompleted(payload.getTrainingPartCompleted());
            req.setDateofCompletion(payload.getDateofCompletion());
            req.setDataValidated(payload.getDataValidated());
            req.setTrainRequest(schoolNameRequest);
            trainingRepo.save(req);
            return ResponseEntity.ok("Training info is Updated ");
        }
        else {
            // Handle the case where the specified SchoolNameRequest does not exist
            return ResponseEntity.badRequest().body("School with ID " + schoolId + " not found");
        }
    }

    public ResponseEntity<?> findTraining(Long schoolId) {
        Optional<SchoolNameRequest> schoolOptional = schoolNameRepo.findById(schoolId);
        if (schoolOptional.isEmpty()) {
            return new ResponseEntity<String>("School details not found !! ", HttpStatus.NOT_FOUND);
        }
        TrainingRequest req = schoolOptional.get().getTrainingRequest();

        TrainingResponse resp = new TrainingResponse();
        resp.setId(req.getId());
        resp.setTrainTheTrainersId(req.getTrainTheTrainersId());
        resp.setTrainTheTrainerHeadId(req.getTrainTheTrainerHeadId());
        resp.setTrainingPartCompleted(req.getTrainingPartCompleted());
        resp.setDateofCompletion(req.getDateofCompletion());
        resp.setDataValidated(req.getDataValidated());
        return ResponseEntity.ok(resp);
    }

}
