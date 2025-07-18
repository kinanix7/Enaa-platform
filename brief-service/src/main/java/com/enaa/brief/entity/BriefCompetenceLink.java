package com.enaa.brief.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents a link between a Brief and a Competence.
 * This entity is mapped to a database table.
 */
@Entity
@Data
public class BriefCompetenceLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long briefId;
    private Long competenceId;
}
