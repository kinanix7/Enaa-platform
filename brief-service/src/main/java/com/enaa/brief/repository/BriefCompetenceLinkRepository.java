package com.enaa.brief.repository;

import com.enaa.brief.entity.BriefCompetenceLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link BriefCompetenceLink} entities.
 * Provides standard JPA operations and custom queries.
 */
public interface BriefCompetenceLinkRepository extends JpaRepository<BriefCompetenceLink, Long> {
    /**
     * Finds all BriefCompetenceLink entities associated with a specific Brief ID.
     * @param briefId The ID of the Brief.
     * @return A list of BriefCompetenceLink entities.
     */
    List<BriefCompetenceLink> findByBriefId(Long briefId);

    /**
     * Finds a BriefCompetenceLink entity by Brief ID and Competence ID.
     * @param briefId The ID of the Brief.
     * @param competenceId The ID of the Competence.
     * @return An Optional containing the BriefCompetenceLink if found, otherwise empty.
     */
    Optional<BriefCompetenceLink> findByBriefIdAndCompetenceId(Long briefId, Long competenceId);
}
