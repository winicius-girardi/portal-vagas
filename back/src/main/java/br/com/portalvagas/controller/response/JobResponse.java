package br.com.portalvagas.controller.response;

import br.com.portalvagas.enums.ExpertiseLevel;
import br.com.portalvagas.enums.State;
import br.com.portalvagas.enums.TypeOfJob;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record JobResponse(
         String id,
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
