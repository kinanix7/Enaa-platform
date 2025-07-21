package com.enaa.brief.repository;

import com.enaa.brief.entity.BriefCompetenceLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BriefCompetenceLinkRepository extends JpaRepository<BriefCompetenceLink, Long> {
    List<BriefCompetenceLink> findByBriefId(Long briefId);
    Optional<BriefCompetenceLink> findByBriefIdAndCompetenceId(Long briefId, Long competenceId);
}
