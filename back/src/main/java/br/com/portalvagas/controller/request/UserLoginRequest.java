package br.com.portalvagas.controller.request;

public record UserLoginRequest(
    String email,
    String password
) {
}
