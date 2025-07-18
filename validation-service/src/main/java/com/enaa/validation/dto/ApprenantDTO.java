package com.enaa.validation.dto;

import lombok.Data;

/**
 * DTO for representing an Apprenant.
 */
@Data
public class ApprenantDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
}
