package com.enaa.apprenant.repository;

import com.enaa.apprenant.entity.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for {@link Rendu} entities.
 * Provides standard JPA operations and custom queries.
 */
public interface RenduRepository extends JpaRepository<Rendu, Long> {
    /**
     * Finds all Rendu entities associated with a specific Apprenant ID.
     * @param apprenantId The ID of the Apprenant.
     * @return A list of Rendu entities.
     */
    List<Rendu> findByApprenantId(Long apprenantId);
}
