package br.com.portalvagas.controller.request;

import br.com.portalvagas.enums.ExpertiseLevel;
import br.com.portalvagas.enums.State;
import br.com.portalvagas.enums.TypeOfJob;

import java.util.Date;

public record JobRequest(
         String title,
         String description,
         String company,
         String expertiseLevel,
         String typeOfJob,
         String state,
         String city,
         String location,
         boolean accept_pcd,
         boolean temporary
){

}
