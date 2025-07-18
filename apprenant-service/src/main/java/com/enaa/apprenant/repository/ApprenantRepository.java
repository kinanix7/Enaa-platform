package com.enaa.apprenant.repository;

import com.enaa.apprenant.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Apprenant} entities.
 * Provides standard JPA operations.
 */
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
}
