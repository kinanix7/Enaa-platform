package com.enaa.apprenant.service;

import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.repository.RenduRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Rendu} entities.
 * Provides business logic for Rendu-related operations.
 */
@Service
@RequiredArgsConstructor
public class RenduService {

    private final RenduRepository renduRepository;

    /**
     * Creates a new Rendu entity.
     * @param rendu The Rendu entity to create.
     * @return The created Rendu entity.
     */
    public Rendu createRendu(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    /**
     * Retrieves a Rendu entity by its ID.
     * @param id The ID of the Rendu to retrieve.
     * @return The Rendu entity.
     * @throws RuntimeException if the Rendu is not found.
     */
    public Rendu getRenduById(Long id) {
        return renduRepository.findById(id).orElseThrow(() -> new RuntimeException("Rendu not found"));
    }
}
