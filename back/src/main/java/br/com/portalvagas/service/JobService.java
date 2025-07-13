package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.response.JobResponse;
import br.com.portalvagas.entity.Job;
import br.com.portalvagas.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public ResponseEntity<Void> createJob(JobRequest request) {

        try{
            jobRepository.save(Builder.createJob(request));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    public ResponseEntity<JobResponse> findJobById(long id) {
        try {
            Job job=jobRepository.findById(id).orElseThrow(()-> new RuntimeException("Job not found"));
            return ResponseEntity.status(HttpStatus.OK).body(Builder.createJobResponse(job));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Void> deleteJobById(long id) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
