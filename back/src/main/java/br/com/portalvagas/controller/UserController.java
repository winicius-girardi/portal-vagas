package br.com.portalvagas.controller;

import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.controller.response.UserResponse;
import br.com.portalvagas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService ;

    @PostMapping("/v1/user")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest request){

        return userService.createUser(request);

    }

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {

        return null;
    }



}
