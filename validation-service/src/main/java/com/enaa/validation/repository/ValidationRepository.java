package com.enaa.validation.repository;

import com.enaa.validation.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidationRepository extends JpaRepository<Validation, Long> {
    List<Validation> findByApprenantId(Long apprenantId);
}
