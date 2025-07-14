package br.com.portalvagas.controller;

import br.com.portalvagas.controller.request.UserNameRequest;
import br.com.portalvagas.controller.request.UserRequest;
import br.com.portalvagas.controller.request.UserRoleRequest;
import br.com.portalvagas.controller.response.UserNameResponse;
import br.com.portalvagas.controller.response.UserRoleResponse;
import br.com.portalvagas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService ;

    @PostMapping("/v1/user")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest request){

        return userService.createUser(request);

    }

    @PostMapping("/v1/user/email")
    public ResponseEntity<UserNameResponse> getUserName(@RequestBody UserNameRequest request) {

        return userService.findUserByEmail(request.email());
    }

    @PostMapping("/v1/user/role")
    public ResponseEntity<UserRoleResponse> getUserRole(@RequestBody UserRoleRequest request){
        return userService.getRoleUserByEmail(request.email());
    }

}
