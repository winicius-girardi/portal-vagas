package br.com.portalvagas.controller.request;

public record UserRequest(
        String email,
        String name,
        String password
) {
}
