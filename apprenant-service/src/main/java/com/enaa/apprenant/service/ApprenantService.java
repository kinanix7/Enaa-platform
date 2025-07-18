package com.enaa.apprenant.service;

import com.enaa.apprenant.entity.Apprenant;
import com.enaa.apprenant.repository.ApprenantRepository;
import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.repository.RenduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing {@link Apprenant} entities.
 * Provides business logic for Apprenant-related operations.
 */
@Service
@RequiredArgsConstructor
public class ApprenantService {

    private final ApprenantRepository apprenantRepository;
    private final RenduRepository renduRepository;

    /**
     * Retrieves all Apprenant entities.
     * @return A list of all Apprenant entities.
     */
    public List<Apprenant> getAllApprenants() {
        return apprenantRepository.findAll();
    }

    /**
     * Creates a new Apprenant entity.
     * @param apprenant The Apprenant entity to create.
     * @return The created Apprenant entity.
     */
    public Apprenant createApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    /**
     * Retrieves an Apprenant entity by its ID.
     * @param id The ID of the Apprenant to retrieve.
     * @return The Apprenant entity.
     * @throws RuntimeException if the Apprenant is not found.
     */
    public Apprenant getApprenantById(Long id) {
        return apprenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Apprenant not found"));
    }

    /**
     * Updates an existing Apprenant entity.
     * @param id The ID of the Apprenant to update.
     * @param apprenantDetails The updated Apprenant details.
     * @return The updated Apprenant entity.
     * @throws RuntimeException if the Apprenant is not found.
     */
    public Apprenant updateApprenant(Long id, Apprenant apprenantDetails) {
        Apprenant apprenant = apprenantRepository.findById(id).orElseThrow(() -> new RuntimeException("Apprenant not found"));
        apprenant.setPrenom(apprenantDetails.getPrenom());
        apprenant.setNom(apprenantDetails.getNom());
        apprenant.setEmail(apprenantDetails.getEmail());
        return apprenantRepository.save(apprenant);
    }

    /**
     * Retrieves all Rendu entities associated with a specific Apprenant ID.
     * @param apprenantId The ID of the Apprenant.
     * @return A list of Rendu entities.
     */
    public List<Rendu> getRendusByApprenant(Long apprenantId) {
        return renduRepository.findByApprenantId(apprenantId);
    }
}
