package com.enaa.brief.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for representing a SousCompetence (Sub-competence).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SousCompetenceDto {
    private Long id;
    private String titre;
}
