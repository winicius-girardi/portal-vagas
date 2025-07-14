package br.com.portalvagas.exception;

import br.com.portalvagas.exception.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseException extends RuntimeException {
    private ErrorResponse details;
}
