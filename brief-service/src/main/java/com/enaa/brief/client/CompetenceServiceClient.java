package com.enaa.brief.client;

import com.enaa.brief.dto.CompetenceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Client for interacting with the Competence Service.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CompetenceServiceClient {

    private final WebClient webClient;

    @Value("${competence.service.url:http://localhost:8081}")
    private String competenceServiceUrl;

    /**
     * Retrieves a competence by its ID from the Competence Service.
     * @param competenceId The ID of the competence to retrieve.
     * @return A Mono that emits the {@link CompetenceDTO} if found, or completes empty if not found or an error occurs.
     */
    public Mono<CompetenceDTO> getCompetenceById(Long competenceId) {
        return webClient.get()
                .uri(competenceServiceUrl + "/api/competences/{id}", competenceId)
                .retrieve()
                .bodyToMono(CompetenceDTO.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> log.error("Error fetching competence with id {}: {}", competenceId, error.getMessage()))
                .onErrorResume(error -> {
                    log.warn("Competence service unavailable for id {}, returning null", competenceId);
                    return Mono.empty();
                });
    }
}
