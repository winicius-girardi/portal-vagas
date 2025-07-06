package br.com.portalvagas.entity;

import br.com.portalvagas.enums.ExpertiseLevel;
import br.com.portalvagas.enums.State;
import br.com.portalvagas.enums.TypeOfJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Job {

    private String id;
    private String title;
    private String description;
    private String company;
    private Date publishDate;
    private Date expireDate;
    private ExpertiseLevel expertiseLevel;
    private TypeOfJob typeOfJob;
    private boolean temporary;
    private State state;
    private String city;
    private String location;
    private boolean accept_pcd;
}
