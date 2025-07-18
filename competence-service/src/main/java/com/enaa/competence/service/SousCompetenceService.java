package com.enaa.competence.service;

import com.enaa.competence.entity.SousCompetence;
import com.enaa.competence.repository.SousCompetenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SousCompetenceService {

    private final SousCompetenceRepository sousCompetenceRepository;

    public SousCompetence updateSousCompetence(Long id, SousCompetence sousCompetenceDetails) {
        SousCompetence sousCompetence = sousCompetenceRepository.findById(id).orElseThrow(() -> new RuntimeException("SousCompetence not found"));
        sousCompetence.setTitre(sousCompetenceDetails.getTitre());
        return sousCompetenceRepository.save(sousCompetence);
    }

    public void deleteSousCompetence(Long id) {
        sousCompetenceRepository.deleteById(id);
    }
}
