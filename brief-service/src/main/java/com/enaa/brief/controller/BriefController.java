package com.enaa.brief.controller;

import com.enaa.brief.dto.BriefResponseDTO;
import com.enaa.brief.service.BriefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.enaa.brief.entity.Brief;
import com.enaa.brief.entity.BriefCompetenceLink;

/**
 * REST controller for managing {@link Brief} resources.
 */
@RestController
@RequestMapping("/api/briefs")
@RequiredArgsConstructor
public class BriefController {

    private final BriefService briefService;

    /**
     * Retrieves all Brief entities.
     * @param ids Optional list of Brief IDs to filter by.
     * @return A list of Brief entities.
     */
    @GetMapping
    public List<Brief> getAllBriefs(@RequestParam(required = false) List<Long> ids) {
        return briefService.getAllBriefs(ids);
    }

    /**
     * Creates a new Brief entity.
     * @param brief The Brief entity to create.
     * @return The created Brief entity.
     */
    @PostMapping
    public Brief createBrief(@RequestBody Brief brief) {
        return briefService.createBrief(brief);
    }

    /**
     * Retrieves a Brief entity by its ID and constructs a {@link BriefResponseDTO}.
     * @param id The ID of the Brief to retrieve.
     * @return A {@link BriefResponseDTO} containing the Brief details and associated competences.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BriefResponseDTO> getBriefById(@PathVariable Long id) {
        return ResponseEntity.ok(briefService.getBriefById(id));
    }

    /**
     * Updates an existing Brief entity.
     * @param id The ID of the Brief to update.
     * @param briefDetails The updated Brief details.
     * @return The updated Brief entity.
     */
    @PutMapping("/{id}")
    public Brief updateBrief(@PathVariable Long id, @RequestBody Brief briefDetails) {
        return briefService.updateBrief(id, briefDetails);
    }

    /**
     * Deletes a Brief entity by its ID.
     * @param id The ID of the Brief to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteBrief(@PathVariable Long id) {
        briefService.deleteBrief(id);
    }

    /**
     * Adds a competence to a brief by creating a link between them.
     * @param briefId The ID of the Brief.
     * @param competenceId The ID of the Competence.
     * @return The created {@link BriefCompetenceLink} entity.
     */
    @PostMapping("/{briefId}/competences")
    public BriefCompetenceLink addCompetenceToBrief(@PathVariable Long briefId, @RequestBody Long competenceId) {
        return briefService.addCompetenceToBrief(briefId, competenceId);
    }

    /**
     * Removes a competence from a brief.
     * @param briefId The ID of the Brief.
     * @param competenceId The ID of the Competence.
     */
    @DeleteMapping("/{briefId}/competences/{competenceId}")
    public void removeCompetenceFromBrief(@PathVariable Long briefId, @PathVariable Long competenceId) {
        briefService.removeCompetenceFromBrief(briefId, competenceId);
    }
}
