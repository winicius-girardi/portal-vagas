package br.com.portalvagas.service;

import br.com.portalvagas.builder.Builder;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseEntity<Void> createUser(UserRequest request) {

        try {

            userRepository.save(Builder.createUser(request));

        }catch(Exception e) {

            return ResponseEntity.internalServerError().build();

        }

        return ResponseEntity.ok().build();
    }

}
