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

import static br.com.portalvagas.builder.Builder.createJobCardPageResponse;
import static br.com.portalvagas.builder.Builder.createJobCardResponseFunction;

@Service
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final ValidatorService validatorService;

    public ResponseEntity<Void> createJob(JobRequest request) {

        try{
            validatorService.validateJobRequest(request);
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

    public JobCardPageResponse searchJobs(SearchRequest request) {
        int page = request.page() != null ? request.page() : 0;
        int size = request.size() != null ? request.size() : 10;

        Sort sort = request.mostRecent()
                ? Sort.by(Sort.Direction.DESC, "publishDate")
                : Sort.by(Sort.Direction.ASC, "id");

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Job> jobPage = jobRepository.searchJobs(request.searchField(), pageable);

        List<JobCardResponse> content = jobPage.getContent().stream()
                .map(createJobCardResponseFunction())
                .toList();

        return createJobCardPageResponse(content, jobPage);
    }



}
