package com.enaa.brief.service;

import com.enaa.brief.entity.Brief;
import com.enaa.brief.repository.BriefRepository;
import com.enaa.brief.entity.BriefCompetenceLink;
import com.enaa.brief.repository.BriefCompetenceLinkRepository;
import com.enaa.brief.dto.BriefResponseDTO;
import com.enaa.brief.dto.CompetenceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class for managing {@link Brief} entities.
 * Provides business logic for Brief-related operations.
 */
@Service
@RequiredArgsConstructor
public class BriefService {

    private final BriefRepository briefRepository;
    private final BriefCompetenceLinkRepository briefCompetenceLinkRepository;
    private final RestTemplate restTemplate;

    /**
     * Retrieves all Brief entities.
     * @param ids Optional list of Brief IDs to filter by.
     * @return A list of Brief entities.
     */
    public List<Brief> getAllBriefs(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return briefRepository.findAllById(ids);
        }
        return briefRepository.findAll();
    }

    /**
     * Creates a new Brief entity.
     * @param brief The Brief entity to create.
     * @return The created Brief entity.
     */
    public Brief createBrief(Brief brief) {
        return briefRepository.save(brief);
    }

    /**
     * Retrieves a Brief entity by its ID and constructs a {@link BriefResponseDTO}.
     * @param id The ID of the Brief to retrieve.
     * @return A {@link BriefResponseDTO} containing the Brief details and associated competences.
     * @throws RuntimeException if the Brief is not found.
     */
    public BriefResponseDTO getBriefById(Long id) {
        Brief brief = briefRepository.findById(id).orElseThrow(() -> new RuntimeException("Brief not found"));
        List<BriefCompetenceLink> links = briefCompetenceLinkRepository.findByBriefId(id);
        List<Long> competenceIds = links.stream().map(BriefCompetenceLink::getCompetenceId).collect(Collectors.toList());

        CompetenceDTO[] competences = null;
        if (!competenceIds.isEmpty()) {
            competences = restTemplate.getForObject("http://localhost:8081/api/competences?ids=" + competenceIds.stream().map(String::valueOf).collect(Collectors.joining(",")),
                    CompetenceDTO[].class);
        }

        BriefResponseDTO responseDTO = new BriefResponseDTO();
        responseDTO.setId(brief.getId());
        responseDTO.setTitre(brief.getTitre());
        responseDTO.setDescription(brief.getDescription());
        responseDTO.setCompetences(competences != null ? Arrays.asList(competences) : List.of());

        return responseDTO;
    }

    /**
     * Updates an existing Brief entity.
     * @param id The ID of the Brief to update.
     * @param briefDetails The updated Brief details.
     * @return The updated Brief entity.
     * @throws RuntimeException if the Brief is not found.
     */
    public Brief updateBrief(Long id, Brief briefDetails) {
        Brief brief = briefRepository.findById(id).orElseThrow(() -> new RuntimeException("Brief not found"));
        brief.setTitre(briefDetails.getTitre());
        brief.setDescription(briefDetails.getDescription());
        return briefRepository.save(brief);
    }

    /**
     * Deletes a Brief entity by its ID.
     * @param id The ID of the Brief to delete.
     */
    public void deleteBrief(Long id) {
        briefRepository.deleteById(id);
    }

    /**
     * Adds a competence to a brief by creating a link between them.
     * @param briefId The ID of the Brief.
     * @param competenceId The ID of the Competence.
     * @return The created {@link BriefCompetenceLink} entity.
     */
    public BriefCompetenceLink addCompetenceToBrief(Long briefId, Long competenceId) {
        BriefCompetenceLink link = new BriefCompetenceLink();
        link.setBriefId(briefId);
        link.setCompetenceId(competenceId);
        return briefCompetenceLinkRepository.save(link);
    }

    /**
     * Removes a competence from a brief.
     * @param briefId The ID of the Brief.
     * @param competenceId The ID of the Competence.
     * @throws RuntimeException if the link is not found.
     */
    public void removeCompetenceFromBrief(Long briefId, Long competenceId) {
        BriefCompetenceLink link = briefCompetenceLinkRepository.findByBriefIdAndCompetenceId(briefId, competenceId)
                .orElseThrow(() -> new RuntimeException("Link not found"));
        briefCompetenceLinkRepository.delete(link);
    }
}
