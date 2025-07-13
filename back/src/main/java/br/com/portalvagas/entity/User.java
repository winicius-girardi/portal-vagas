package br.com.portalvagas.entity;

import br.com.portalvagas.enums.RoleUser;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user",schema = "sch_portal_vagas")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;
    private String password;
    private String email;

    @Column(name = "role")
    private RoleUser roleUser;
}
