package br.com.portalvagas.exception.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponseList(
        List<ErrorResponse> detalis
) {
}
