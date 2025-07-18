package com.enaa.apprenant.controller;

import com.enaa.apprenant.entity.Rendu;
import com.enaa.apprenant.service.RenduService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link Rendu} resources.
 */
@RestController
@RequestMapping("/api/rendus")
@RequiredArgsConstructor
public class RenduController {

    private final RenduService renduService;

    /**
     * Creates a new Rendu entity.
     * @param rendu The Rendu entity to create.
     * @return The created Rendu entity.
     */
    @PostMapping
    public Rendu createRendu(@RequestBody Rendu rendu) {
        return renduService.createRendu(rendu);
    }

    /**
     * Retrieves a Rendu entity by its ID.
     * @param id The ID of the Rendu to retrieve.
     * @return The Rendu entity.
     */
    @GetMapping("/{id}")
    public Rendu getRenduById(@PathVariable Long id) {
        return renduService.getRenduById(id);
    }
}
