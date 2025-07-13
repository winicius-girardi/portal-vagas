package br.com.portalvagas.controller;


import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.SearchRequest;
import br.com.portalvagas.controller.response.JobCardPageResponse;
import br.com.portalvagas.controller.response.JobCardResponse;
import br.com.portalvagas.controller.response.JobResponse;
import br.com.portalvagas.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    @PostMapping("/v1/jobs")
    public ResponseEntity<Void> createJob(@RequestBody JobRequest request) {
        return jobService.createJob(request);
    }

    @GetMapping("/v1/jobs/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable long id) {
        return jobService.findJobById(id);
    }

    // TODO -> THIS ONLY SHOULD DELETE REQUEST MADE BY A USER WITH ADMIN RIGHTS
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable long id) {
        return jobService.deleteJobById(id);
    }

    //TODO -> SHOULD RETURN ALL JOBS, BASED ON MOST RECENT OR LEAST RECENT, THE SEARCH FIELD AND MAYBE THE STATE AND CITY
    @PostMapping("/v1/jobs/search")
    public ResponseEntity<JobCardPageResponse> getAllJobs(@RequestBody SearchRequest request) {
        return jobService.searchJobsByParameters(request);
    }

}
