package com.enaa.apprenant.service;

import com.enaa.apprenant.entity.Apprenant;
import com.enaa.apprenant.repository.ApprenantRepository;
import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.repository.RenduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprenantService {

    private final ApprenantRepository apprenantRepository;
    private final RenduRepository renduRepository;

    public List<Apprenant> getAllApprenants() {
        return apprenantRepository.findAll();
    }

    public Apprenant createApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    public Apprenant getApprenantById(Long id) {
        return apprenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Apprenant not found"));
    }

    public Apprenant updateApprenant(Long id, Apprenant apprenantDetails) {
        Apprenant apprenant = apprenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Apprenant not found"));
        apprenant.setPrenom(apprenantDetails.getPrenom());
        apprenant.setNom(apprenantDetails.getNom());
        apprenant.setEmail(apprenantDetails.getEmail());
        return apprenantRepository.save(apprenant);
    }

    public List<Rendu> getRendusByApprenant(Long apprenantId) {
        return renduRepository.findByApprenantId(apprenantId);
    }
}
