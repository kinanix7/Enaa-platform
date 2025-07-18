package com.enaa.validation.client;

import com.enaa.validation.dto.ApprenantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApprenantServiceClient {

    private final WebClient webClient;

    @Value("${apprenant.service.url:http://localhost:8083}")
    private String apprenantServiceUrl;

    public Mono<ApprenantDTO> getApprenantById(Long apprenantId) {
        return webClient.get()
                .uri(apprenantServiceUrl + "/api/apprenants/{id}", apprenantId)
                .retrieve()
                .bodyToMono(ApprenantDTO.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> log.error("Error fetching apprenant with id {}: {}", apprenantId, error.getMessage()))
                .onErrorResume(error -> {
                    log.warn("Apprenant service unavailable for id {}", apprenantId);
                    return Mono.empty();
                });
    }
}
