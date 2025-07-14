package br.com.portalvagas.exception.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String ErrorField,
        String Message
) {
}
