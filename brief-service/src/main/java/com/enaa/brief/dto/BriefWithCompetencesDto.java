package com.enaa.brief.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BriefWithCompetencesDto {
    private Long id;
    private String titre;
    private String description;
    private List<CompetenceDTO> competences;
}
