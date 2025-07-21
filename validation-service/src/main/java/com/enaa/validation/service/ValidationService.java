package com.enaa.validation.service;

import com.enaa.validation.enums.Statut;

import com.enaa.validation.entity.Validation;
import com.enaa.validation.repository.ValidationRepository;
import com.enaa.validation.dto.DashboardDTO;
import com.enaa.validation.dto.ApprenantDTO;
import com.enaa.validation.dto.BriefDTO;
import com.enaa.validation.dto.CompetenceDTO;
import com.enaa.validation.dto.ValidationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationRepository validationRepository;
    private final RestTemplate restTemplate;

    public List<Validation> getAllValidations() {
        return validationRepository.findAll();
    }

    public Validation createValidation(Validation validation) {
        try {
            restTemplate.getForObject("http://localhost:8083/api/apprenants/" + validation.getApprenantId(), ApprenantDTO.class);
            restTemplate.getForObject("http://localhost:8082/api/briefs/" + validation.getBriefId(), BriefDTO.class);
            restTemplate.getForObject("http://localhost:8081/api/competences/" + validation.getCompetenceId(), CompetenceDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error validating entities: " + e.getMessage());
        }
        return validationRepository.save(validation);
    }

    public Validation updateValidation(Long id, Validation validationDetails) {
        Validation validation = validationRepository.findById(id).orElseThrow(() -> new RuntimeException("Validation not found"));
        validation.setStatut(validationDetails.getStatut());
        return validationRepository.save(validation);
    }

    public DashboardDTO getDashboard(Long apprenantId) {
        ApprenantDTO apprenant = restTemplate.getForObject("http://localhost:8083/api/apprenants/" + apprenantId, ApprenantDTO.class);
        if (apprenant == null) {
            throw new RuntimeException("Apprenant not found");
        }

        List<Validation> validations = validationRepository.findByApprenantId(apprenantId);

        List<Long> competenceIds = validations.stream().map(Validation::getCompetenceId).distinct().collect(Collectors.toList());
        List<Long> briefIds = validations.stream().map(Validation::getBriefId).distinct().collect(Collectors.toList());

        CompetenceDTO[] competencesArray = restTemplate.getForObject("http://localhost:8081/api/competences?ids=" + competenceIds.stream().map(String::valueOf).collect(Collectors.joining(",")),
                CompetenceDTO[].class);
        Map<Long, CompetenceDTO> competencesMap = Arrays.stream(competencesArray).collect(Collectors.toMap(CompetenceDTO::getId, Function.identity()));

        BriefDTO[] briefsArray = restTemplate.getForObject("http://localhost:8082/api/briefs?ids=" + briefIds.stream().map(String::valueOf).collect(Collectors.joining(",")),
                BriefDTO[].class);
        Map<Long, BriefDTO> briefsMap = Arrays.stream(briefsArray).collect(Collectors.toMap(BriefDTO::getId, Function.identity()));

        List<ValidationDTO> validationDTOs = validations.stream().map(validation -> {
            ValidationDTO dto = new ValidationDTO();
            dto.setId(validation.getId());
            dto.setStatut(validation.getStatut());
            dto.setCompetence(competencesMap.get(validation.getCompetenceId()));
            dto.setBrief(briefsMap.get(validation.getBriefId()));
            return dto;
        }).collect(Collectors.toList());

        DashboardDTO dashboardDTO = new DashboardDTO();
        dashboardDTO.setApprenant(apprenant);
        dashboardDTO.setValidations(validationDTOs);

        return dashboardDTO;
    }
}
