package br.com.portalvagas.exception;


import br.com.portalvagas.exception.response.ErrorResponse;
import br.com.portalvagas.exception.response.ErrorResponseList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.portalvagas.builder.Builder.createErrorResponseList;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(400).body(ex.getDetails());
    }

    @ExceptionHandler(ValidationListException.class)
    public ResponseEntity<ErrorResponseList> handleValidationListException(ValidationListException ex) {
        return ResponseEntity.status(400).body(createErrorResponseList(ex.getDetails()));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseException ex) {
        return ResponseEntity.status(409).body(ex.getDetails());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .ErrorField("authentication")
                .Message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .ErrorField("authorization")
                .Message("Acesso negado.")
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .ErrorField("Internal Server Error")
                .Message(ex.getMessage())
                .build();
        return ResponseEntity.status(500).body(errorResponse);
    }

}
