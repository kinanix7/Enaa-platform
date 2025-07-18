package com.enaa.apprenant.controller;

import com.enaa.apprenant.entity.Apprenant;
import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.service.ApprenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link Apprenant} resources.
 */
@RestController
@RequestMapping("/api/apprenants")
@RequiredArgsConstructor
public class ApprenantController {

    private final ApprenantService apprenantService;

    /**
     * Retrieves all Apprenant entities.
     * @return A list of all Apprenant entities.
     */
    @GetMapping
    public List<Apprenant> getAllApprenants() {
        return apprenantService.getAllApprenants();
    }

    /**
     * Creates a new Apprenant entity.
     * @param apprenant The Apprenant entity to create.
     * @return The created Apprenant entity.
     */
    @PostMapping
    public Apprenant createApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.createApprenant(apprenant);
    }

    /**
     * Retrieves an Apprenant entity by its ID.
     * @param id The ID of the Apprenant to retrieve.
     * @return The Apprenant entity.
     */
    @GetMapping("/{id}")
    public Apprenant getApprenantById(@PathVariable Long id) {
        return apprenantService.getApprenantById(id);
    }

    /**
     * Updates an existing Apprenant entity.
     * @param id The ID of the Apprenant to update.
     * @param apprenantDetails The updated Apprenant details.
     * @return The updated Apprenant entity.
     */
    @PutMapping("/{id}")
    public Apprenant updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenantDetails) {
        return apprenantService.updateApprenant(id, apprenantDetails);
    }

    /**
     * Retrieves all Rendu entities associated with a specific Apprenant ID.
     * @param apprenantId The ID of the Apprenant.
     * @return A list of Rendu entities.
     */
    @GetMapping("/{apprenantId}/rendus")
    public List<Rendu> getRendusByApprenant(@PathVariable Long apprenantId) {
        return apprenantService.getRendusByApprenant(apprenantId);
    }
}
