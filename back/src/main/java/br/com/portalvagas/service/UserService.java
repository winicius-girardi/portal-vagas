package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.controller.response.UserNameResponse;
import br.com.portalvagas.entity.User;
import br.com.portalvagas.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final ValidatorService validatorService;
    private final UserRepository userRepository;

    public ResponseEntity<Void> createUser(UserRequest request) {

        try {
            validatorService.validateUserRequest(request);
            userRepository.save(Builder.createUser(request));

        }catch(Exception e) {

            return ResponseEntity.internalServerError().build();

        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserNameResponse> findUserByEmail(String email) {

        validatorService.validateEmailNull(email);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return ResponseEntity.ok(Builder.createUserNameResponse(user.getName()));
    }
}
