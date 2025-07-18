package com.enaa.validation.dto;

import com.enaa.validation.enums.Statut;
import lombok.Data;

/**
 * DTO for representing a Validation.
 */
@Data
public class ValidationDTO {
    private Long id;
    private Statut statut;
    private CompetenceDTO competence;
    private BriefDTO brief;
}
