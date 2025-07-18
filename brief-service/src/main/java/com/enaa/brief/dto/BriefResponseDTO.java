package com.enaa.brief.dto;

import com.enaa.brief.entity.Brief;
import lombok.Data;

import java.util.List;

/**
 * DTO for representing a Brief with its associated competences.
 */
@Data
public class BriefResponseDTO {
    private Long id;
    private String titre;
    private String description;
    private List<CompetenceDTO> competences;
}
