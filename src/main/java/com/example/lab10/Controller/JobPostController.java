package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobPost;

import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/getall")
    public ResponseEntity getAllJobPost(){
        if(jobPostService.getAllJobPost()!=null){
            return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
        }
        return ResponseEntity.status(404).body("there is no job post in DB");
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            jobPostService.addJobPost(jobPost);
            return ResponseEntity.status(201).body(new ApiResponse("added job post successfully"));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @Valid @RequestBody JobPost jobPost,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            if (jobPostService.updateJobPost(jobPost,id)){
                return ResponseEntity.status(201).body(new ApiResponse("Update successful"));
            }
            return ResponseEntity.status(404).body(new ApiResponse("the job post you want to update failed"));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){
        if (jobPostService.deleteJobPost(id)){
            return ResponseEntity.status(201).body(new ApiResponse("Delete successful"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("the job post you want to delete failed"));
    }
}
