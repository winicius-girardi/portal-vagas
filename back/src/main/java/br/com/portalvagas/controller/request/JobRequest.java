package br.com.portalvagas.controller.request;

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
