package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.SearchRequest;
import br.com.portalvagas.controller.response.JobCardPageResponse;
import br.com.portalvagas.controller.response.JobCardResponse;
import br.com.portalvagas.controller.response.JobResponse;
import br.com.portalvagas.entity.Job;
import br.com.portalvagas.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<JobCardPageResponse> searchJobsByParameters(SearchRequest request) {
        Pageable pageable = PageRequest.of(
                request.page() != null ? request.page() : 0,
                request.size() != null ? request.size() : 10,
                request.mostRecent() ? Sort.by("publishDate").descending() : Sort.by("id").ascending()
        );

        Page<Job> jobs = jobRepository.findJobsByFilters(
                request.searchField(),
                request.city(),
                request.state(),
                pageable
        );

        List<JobCardResponse> content = jobs.getContent().stream()
                .map(job -> new JobCardResponse(
                        job.getId(),
                        job.getTitle(),
                        job.getCompany(),
                        job.getPublishDate().toString(),
                        job.getExpireDate().toString(),
                        job.getCity(),
                        job.getState().name(),
                        job.isTemporary()
                ))
                .toList();

        JobCardPageResponse response = new JobCardPageResponse(
                content,
                jobs.getNumber(),
                jobs.getSize(),
                jobs.getTotalElements(),
                jobs.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }
}
