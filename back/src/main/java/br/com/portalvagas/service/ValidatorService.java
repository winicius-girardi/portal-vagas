package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.request.JobRequest;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.exception.DatabaseException;
import br.com.portalvagas.exception.ValidationException;
import br.com.portalvagas.exception.ValidationListException;
import br.com.portalvagas.exception.response.ErrorResponse;
import br.com.portalvagas.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ValidatorService {

    private final UserRepository userRepository;

    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ValidationException(Builder.createErrorResponse(
                "Email",
                "The email provided is either null or empty."
            ));
        }
        if (!email.contains("@")) {
            throw new ValidationException(Builder.createErrorResponse(
                "Email",
                "The email provided is invalid. It must contain an '@' character."
            ));
        }
        if (userRepository.existsByEmail(email)) {
            throw new DatabaseException(Builder.createErrorResponse(
                "Email",
                "The email provided is already registered in the system."
            ));
        }

    }
    public void validateJobRequest(JobRequest request) {
        List<ErrorResponse> errors = new ArrayList<>();

        if (isBlank(request.title())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("title")
                    .Message("Título é obrigatório.")
                    .build());
        }

        if (isBlank(request.description())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("description")
                    .Message("Descrição é obrigatória.")
                    .build());
        }

        if (isBlank(request.company())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("company")
                    .Message("Empresa é obrigatória.")
                    .build());
        }

        if (isBlank(request.expertiseLevel())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("expertiseLevel")
                    .Message("Nível de expertise é obrigatório.")
                    .build());
        }

        if (isBlank(request.typeOfJob())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("typeOfJob")
                    .Message("Tipo de vaga é obrigatório.")
                    .build());
        }

        if (isBlank(request.state())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("state")
                    .Message("Estado é obrigatório.")
                    .build());
        }

        if (isBlank(request.city())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("city")
                    .Message("Cidade é obrigatória.")
                    .build());
        }

        if (isBlank(request.location())) {
            errors.add(ErrorResponse.builder()
                    .ErrorField("location")
                    .Message("Localização é obrigatória.")
                    .build());
        }

        if (!errors.isEmpty()) {
            throw new ValidationListException(Builder.createErrorResponseList(errors).detalis());
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public void validateEmailNull(String email){
        if (isBlank(email)) {
            throw new ValidationException(Builder.createErrorResponse(
                "Email",
                "The email provided is either null or empty."
            ));
        }
    }


    public void validateUserRequest(UserRequest request) {
        validateEmail(request.email());

    }
}
