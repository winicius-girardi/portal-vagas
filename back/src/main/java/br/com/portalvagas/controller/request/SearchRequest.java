package br.com.portalvagas.controller.request;

public record SearchRequest(

        boolean mostRecent,
        String searchField,
        String city,
        String state,
        Integer page,
        Integer size
){
}
