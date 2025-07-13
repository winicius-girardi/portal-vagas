package br.com.portalvagas.controller.response;

import java.util.List;

public record JobCardPageResponse(
        List<JobCardResponse> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}

