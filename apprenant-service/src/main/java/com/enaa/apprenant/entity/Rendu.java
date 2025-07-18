package com.enaa.apprenant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

/**
 * Represents a Rendu (Submission) entity in the system.
 * This entity is mapped to a database table.
 */
@Entity
@Data
public class Rendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private Date dateSoumission;
    private Long apprenantId;
    private Long briefId;
}
