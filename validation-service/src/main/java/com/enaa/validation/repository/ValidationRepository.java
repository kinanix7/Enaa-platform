package com.enaa.validation.repository;

import com.enaa.validation.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for {@link Validation} entities.
 * Provides standard JPA operations and custom queries.
 */
public interface ValidationRepository extends JpaRepository<Validation, Long> {
    /**
     * Finds all Validation entities associated with a specific Apprenant ID.
     * @param apprenantId The ID of the Apprenant.
     * @return A list of Validation entities.
     */
    List<Validation> findByApprenantId(Long apprenantId);
}
