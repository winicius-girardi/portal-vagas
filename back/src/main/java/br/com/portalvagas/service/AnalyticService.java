package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.response.AnalyticsResponse;
import br.com.portalvagas.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnalyticService {

    private final JobRepository jobRepository;

    public ResponseEntity<AnalyticsResponse> getAnalyticsData() {
        long totalJobs;
        long totalCltJobs;
        long totalInternJobs;
        long totalPJJobs;
        totalJobs=jobRepository.count();
        totalCltJobs=jobRepository.countByTypeOfJob("CLT");
        totalPJJobs=jobRepository.countByTypeOfJob("PJ");
        totalInternJobs=jobRepository.countByTypeOfJob("INTERNSHIP");
        List<Object[]> rawJobsPerDay = jobRepository.countVagasPorDiaUltimos30Dias();
        Map<String, Long> jobsPerDay = rawJobsPerDay.stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> ((Number) r[1]).longValue()
                ));
        return ResponseEntity.ok().body(Builder.createAnalyticsResponse(totalJobs,totalInternJobs,totalPJJobs,totalCltJobs,jobsPerDay));
    }
}
