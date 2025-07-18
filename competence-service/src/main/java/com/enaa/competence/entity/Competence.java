package com.enaa.competence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String titre;
    private String description;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL)
    private List<SousCompetence> sousCompetences;
}
