package br.com.portalvagas.controller.response;

public record JobCardResponse(
        int id,
        String title,
        String company,
        String publishDate,
        String expireDate,
        String city,
        String state,
        boolean temporary
) {}
