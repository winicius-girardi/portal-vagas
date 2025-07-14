package br.com.portalvagas.controller.request;

public record SearchRequest(

        boolean mostRecent,
        String searchField,
        Integer page,
        Integer size
){
}
