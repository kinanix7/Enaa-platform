package com.enaa.brief.controller;

import com.enaa.brief.dto.BriefResponseDTO;
import com.enaa.brief.service.BriefService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.enaa.brief.entity.Brief;
import com.enaa.brief.entity.BriefCompetenceLink;

@RestController
@RequestMapping("/api/briefs")
@RequiredArgsConstructor
public class BriefController {

    private final BriefService briefService;

    @GetMapping
    public List<Brief> getAllBriefs(@RequestParam(required = false) List<Long> ids) {
        return briefService.getAllBriefs(ids);
    }

    @PostMapping
    public Brief createBrief(@RequestBody Brief brief) {
        return briefService.createBrief(brief);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BriefResponseDTO> getBriefById(@PathVariable Long id) {
        return ResponseEntity.ok(briefService.getBriefById(id));
    }

    @PutMapping("/{id}")
    public Brief updateBrief(@PathVariable Long id, @RequestBody Brief briefDetails) {
        return briefService.updateBrief(id, briefDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBrief(@PathVariable Long id) {
        briefService.deleteBrief(id);
    }

    @PostMapping("/{briefId}/competences")
    public BriefCompetenceLink addCompetenceToBrief(@PathVariable Long briefId, @RequestBody Long competenceId) {
        return briefService.addCompetenceToBrief(briefId, competenceId);
    }

    @DeleteMapping("/{briefId}/competences/{competenceId}")
    public void removeCompetenceFromBrief(@PathVariable Long briefId, @PathVariable Long competenceId) {
        briefService.removeCompetenceFromBrief(briefId, competenceId);
    }
}
