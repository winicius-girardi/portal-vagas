package br.com.portalvagas.controller.response;

import lombok.Builder;

@Builder
public record UserNameResponse(
    String name
){
}
