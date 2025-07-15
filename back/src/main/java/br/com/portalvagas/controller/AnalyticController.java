package br.com.portalvagas.controller;

import br.com.portalvagas.controller.response.AnalyticsResponse;
import br.com.portalvagas.service.AnalyticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@AllArgsConstructor
public class AnalyticController {

    private final AnalyticService analyticsService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/resume")
    public ResponseEntity<AnalyticsResponse> getAnalytics(){
        return analyticsService.getAnalyticsData();
    }
}
