package com.enaa.validation.entity;

import com.enaa.validation.enums.Statut;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long apprenantId;
    private Long competenceId;
    private Long briefId;

    @Enumerated(EnumType.STRING)
    private Statut statut;
}
