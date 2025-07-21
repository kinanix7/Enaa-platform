package com.enaa.apprenant.repository;

import com.enaa.apprenant.entity.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RenduRepository extends JpaRepository<Rendu, Long> {
    List<Rendu> findByApprenantId(Long apprenantId);
}
