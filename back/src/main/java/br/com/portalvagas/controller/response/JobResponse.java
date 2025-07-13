package br.com.portalvagas.controller.response;

import lombok.Builder;

@Builder
public record JobResponse(
         int id,
         String title,
         String description,
         String company,
         String publishDate,
         String expireDate,
         String expertiseLevel,
         String typeOfJob,
         boolean temporary,
         String state,
         String city,
         String location,
         boolean accept_pcd
){
}
