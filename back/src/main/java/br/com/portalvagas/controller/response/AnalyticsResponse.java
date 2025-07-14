package br.com.portalvagas.controller.response;

import lombok.Builder;

import java.util.Map;
@Builder
public record AnalyticsResponse(

    long vagasTotal,
    long vagaEstagio,
    long vagaCLT,
    long vagaPJ,
    Map<String, Long> vagasPorDia
){
}
