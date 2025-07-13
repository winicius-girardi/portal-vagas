package br.com.portalvagas.controller;

import br.com.portalvagas.controller.request.UserLoginRequest;
import br.com.portalvagas.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authManager;

    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest user) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
        return jwtUtil.generateToken(user.email());
    }

}
