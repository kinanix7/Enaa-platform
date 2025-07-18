package com.enaa.competence.repository;

import com.enaa.competence.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
