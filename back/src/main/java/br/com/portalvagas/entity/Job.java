package br.com.portalvagas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private int id;
    private String title;
    private String description;
    private String company;
    @Column(name="PUBLISH_DATE")
    private LocalDate publishDate;
    @Column(name="EXPIRE_DATE")
    private LocalDate expireDate;
    @Column(name="EXPERTISE_LEVEL")
    private String expertiseLevel;
    @Column(name="TYPE_OF_JOB")
    private String typeOfJob;
    private String state;
    private boolean temporary;
    private String city;
    private String location;
    @Column(name="ACCEPT_PCD")
    private boolean acceptPcd;
}

