package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.UserRepository;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobapp")
public class JobApplicationController {
     private final JobApplicationService jobApplicationService;
    @GetMapping("/getall")
    public ResponseEntity getAllJobApplications() {
        return jobApplicationService.getAllJobApplications().isEmpty()?ResponseEntity.status(400).body("The DB for Job Application Empty"):ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @PostMapping("/add")
    public ResponseEntity applyJobApplication(@Valid @RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {

            if (jobApplicationService.addJobApplication(jobApplication)) {
                return ResponseEntity.status(201).body(new ApiResponse("Job Application Added"));
            }
            return ResponseEntity.status(400).body(new ApiResponse("Job Application Not Added check the ids you add"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id) {
        if (jobApplicationService.deleteJobApplication(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Job Application Deleted"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Job Application Not Found"));
    }
}
