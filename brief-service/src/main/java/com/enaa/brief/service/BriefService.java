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

@Service
@RequiredArgsConstructor
public class BriefService {

    private final BriefRepository briefRepository;
    private final BriefCompetenceLinkRepository briefCompetenceLinkRepository;
    private final RestTemplate restTemplate;

    public List<Brief> getAllBriefs(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return briefRepository.findAllById(ids);
        }
        return briefRepository.findAll();
    }

    public Brief createBrief(Brief brief) {
        return briefRepository.save(brief);
    }

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

    public Brief updateBrief(Long id, Brief briefDetails) {
        Brief brief = briefRepository.findById(id).orElseThrow(() -> new RuntimeException("Brief not found"));
        brief.setTitre(briefDetails.getTitre());
        brief.setDescription(briefDetails.getDescription());
        return briefRepository.save(brief);
    }

    public void deleteBrief(Long id) {
        briefRepository.deleteById(id);
    }

    public BriefCompetenceLink addCompetenceToBrief(Long briefId, Long competenceId) {
        BriefCompetenceLink link = new BriefCompetenceLink();
        link.setBriefId(briefId);
        link.setCompetenceId(competenceId);
        return briefCompetenceLinkRepository.save(link);
    }

    public void removeCompetenceFromBrief(Long briefId, Long competenceId) {
        BriefCompetenceLink link = briefCompetenceLinkRepository.findByBriefIdAndCompetenceId(briefId, competenceId)
                .orElseThrow(() -> new RuntimeException("Link not found"));
        briefCompetenceLinkRepository.delete(link);
    }
}
