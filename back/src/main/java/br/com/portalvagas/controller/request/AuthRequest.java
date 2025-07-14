package br.com.portalvagas.controller.request;

public record AuthRequest(
    String email,
    String password
) {
}
