package com.enaa.competence.controller;

import com.enaa.competence.entity.Competence;
import com.enaa.competence.entity.SousCompetence;

import com.enaa.competence.service.CompetenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competences")
@RequiredArgsConstructor
public class CompetenceController {

    private final CompetenceService competenceService;

    @GetMapping
    public List<Competence> getAllCompetences(@RequestParam(required = false) List<Long> ids) {
        return competenceService.getAllCompetences(ids);
    }

    @PostMapping
    public Competence createCompetence(@RequestBody Competence competence) {
        return competenceService.createCompetence(competence);
    }

    @GetMapping("/{id}")
    public Competence getCompetenceById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id);
    }

    @PutMapping("/{id}")
    public Competence updateCompetence(@PathVariable Long id, @RequestBody Competence competenceDetails) {
        return competenceService.updateCompetence(id, competenceDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
    }

    @PostMapping("/{competenceId}/sous-competences")
    public SousCompetence addSousCompetence(@PathVariable Long competenceId, @RequestBody SousCompetence sousCompetence) {
        return competenceService.addSousCompetence(competenceId, sousCompetence);
    }
}
