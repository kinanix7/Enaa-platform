package com.enaa.validation.dto;

import lombok.Data;

/**
 * DTO for representing a Competence.
 */
@Data
public class CompetenceDTO {
    private Long id;
    private String code;
    private String titre;
    private String description;
}
