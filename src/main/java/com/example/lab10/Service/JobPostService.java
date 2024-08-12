package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll().isEmpty()?null:jobPostRepository.findAll();
    }

    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public boolean updateJobPost(JobPost jobPost,Integer id){
        if (jobPostRepository.existsById(id)){
            JobPost oldJobPost = jobPostRepository.getById(id);
            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setPostingDate(jobPost.getPostingDate());
            oldJobPost.setSalary(jobPost.getSalary());
            oldJobPost.setLocation(jobPost.getLocation());
            jobPostRepository.save(oldJobPost);
            return true;
        }
        return false;
    }

    public boolean deleteJobPost(Integer id){
        if (jobPostRepository.existsById(id)){
            jobPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
