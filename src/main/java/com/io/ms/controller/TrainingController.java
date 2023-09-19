package com.io.ms.controller;
import com.io.ms.entities.school.TrainingRequest;
import com.io.ms.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TrainingController {
    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);
    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping(value = "/Secured/MBP/School/AddTraining")
    public ResponseEntity<?> addTraining(@RequestBody TrainingRequest payload, @RequestParam Long schoolId)  {
        return trainingService.addTraining(payload,schoolId);
    }

    @PutMapping(value = "/Secured/MBP/School/EditTraining")
    public ResponseEntity<?> editTraining(@RequestBody TrainingRequest payload,@RequestParam Long schoolId)  {
        return trainingService.editTraining(payload,schoolId);
    }

    @GetMapping(value = "/Secured/MBP/School/FindTraining")
    public ResponseEntity<?> findOTraining(@RequestParam Long schoolId)  {
        return trainingService.findTraining(schoolId);
    }




}
