package br.com.portalvagas.controller;


import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.SearchRequest;
import br.com.portalvagas.controller.response.JobCardPageResponse;
import br.com.portalvagas.controller.response.JobResponse;
import br.com.portalvagas.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable long id) {
        return jobService.deleteJobById(id);
    }

    @PostMapping("/v1/jobs/search")
    public ResponseEntity<JobCardPageResponse> getAllJobs(@RequestBody SearchRequest request) {
        JobCardPageResponse jobCardPageResponse = jobService.searchJobs(request);
        return ResponseEntity.ok(jobCardPageResponse);
    }

}
