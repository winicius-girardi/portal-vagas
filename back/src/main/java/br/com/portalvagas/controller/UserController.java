package br.com.portalvagas.controller;

import br.com.portalvagas.controller.response.UserResponse;
import br.com.portalvagas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService ;

    @PostMapping("/v1/user")
    public ResponseEntity<Void> createUser(){
        return null;
    }
    @GetMapping("/v1/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return null;
    }



}
