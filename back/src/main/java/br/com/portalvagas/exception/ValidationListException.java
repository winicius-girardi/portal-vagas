package br.com.portalvagas.exception;


import br.com.portalvagas.exception.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationListException extends RuntimeException {
    private List<ErrorResponse> details;
}
