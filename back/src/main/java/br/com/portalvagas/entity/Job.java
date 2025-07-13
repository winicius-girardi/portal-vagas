package br.com.portalvagas.entity;

import br.com.portalvagas.enums.ExpertiseLevel;
import br.com.portalvagas.enums.State;
import br.com.portalvagas.enums.TypeOfJob;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "JOB",schema = "sch_portal_vagas")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String description;
    private String company;
    private LocalDate publishDate;
    private LocalDate expireDate;
    private ExpertiseLevel expertiseLevel;
    private TypeOfJob typeOfJob;
    private boolean temporary;
    private State state;
    private String city;
    private String location;
    private boolean accept_pcd;
}

