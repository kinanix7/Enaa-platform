package com.enaa.competence.controller;

import com.enaa.competence.entity.SousCompetence;
import com.enaa.competence.service.SousCompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sous-competences")
@RequiredArgsConstructor
public class SousCompetenceController {

    private final SousCompetenceService sousCompetenceService;

    @PutMapping("/{id}")
    public SousCompetence updateSousCompetence(@PathVariable Long id, @RequestBody SousCompetence sousCompetenceDetails) {
        return sousCompetenceService.updateSousCompetence(id, sousCompetenceDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSousCompetence(@PathVariable Long id) {
        sousCompetenceService.deleteSousCompetence(id);
    }
}
