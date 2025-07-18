package com.enaa.brief.repository;

import com.enaa.brief.entity.Brief;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Brief} entities.
 * Provides standard JPA operations.
 */
public interface BriefRepository extends JpaRepository<Brief, Long> {
}
