package com.enaa.competence.service;

import com.enaa.competence.entity.Competence;
import com.enaa.competence.repository.CompetenceRepository;
import com.enaa.competence.entity.SousCompetence;
import com.enaa.competence.repository.SousCompetenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetenceService {

    private final CompetenceRepository competenceRepository;
    private final SousCompetenceRepository sousCompetenceRepository;

    public List<Competence> getAllCompetences(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return competenceRepository.findAllById(ids);
        }
        return competenceRepository.findAll();
    }

    public Competence createCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    public Competence getCompetenceById(Long id) {
        return competenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Competence not found"));
    }

    public Competence updateCompetence(Long id, Competence competenceDetails) {
        Competence competence = competenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Competence not found"));
        competence.setCode(competenceDetails.getCode());
        competence.setTitre(competenceDetails.getTitre());
        competence.setDescription(competenceDetails.getDescription());
        return competenceRepository.save(competence);
    }

    public void deleteCompetence(Long id) {
        competenceRepository.deleteById(id);
    }

    public SousCompetence addSousCompetence(Long competenceId, SousCompetence sousCompetence) {
        Competence competence = competenceRepository.findById(competenceId).orElseThrow(() -> new RuntimeException("Competence not found"));
        sousCompetence.setCompetence(competence);
        return sousCompetenceRepository.save(sousCompetence);
    }
}
