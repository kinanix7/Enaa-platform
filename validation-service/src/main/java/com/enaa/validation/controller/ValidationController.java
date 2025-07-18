package com.enaa.validation.controller;

import com.enaa.validation.dto.DashboardDTO;
import com.enaa.validation.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.enaa.validation.entity.Validation;

/**
 * REST controller for managing {@link Validation} resources.
 */
@RestController
@RequestMapping("/api/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    /**
     * Retrieves all Validation entities.
     * @return A list of all Validation entities.
     */
    @GetMapping
    public List<Validation> getAllValidations() {
        return validationService.getAllValidations();
    }

    /**
     * Creates a new Validation entity.
     * @param validation The Validation entity to create.
     * @return The created Validation entity.
     */
    @PostMapping
    public Validation createValidation(@RequestBody Validation validation) {
        return validationService.createValidation(validation);
    }

    /**
     * Updates an existing Validation entity.
     * @param id The ID of the Validation to update.
     * @param validationDetails The updated Validation details.
     * @return The updated Validation entity.
     */
    @PutMapping("/{id}")
    public Validation updateValidation(@PathVariable Long id, @RequestBody Validation validationDetails) {
        return validationService.updateValidation(id, validationDetails);
    }

    /**
     * Retrieves dashboard information for a specific Apprenant.
     * @param apprenantId The ID of the Apprenant.
     * @return A {@link ResponseEntity} containing the {@link DashboardDTO}.
     */
    @GetMapping("/apprenants/{apprenantId}/dashboard")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable Long apprenantId) {
        return ResponseEntity.ok(validationService.getDashboard(apprenantId));
    }
}
