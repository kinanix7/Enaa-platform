package com.enaa.validation.client;

import com.enaa.validation.dto.BriefDTO;
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
public class BriefServiceClient {

    private final WebClient webClient;

    @Value("${brief.service.url:http://localhost:8082}")
    private String briefServiceUrl;

    public Mono<BriefDTO> getBriefById(Long briefId) {
        return webClient.get()
                .uri(briefServiceUrl + "/api/briefs/{id}", briefId)
                .retrieve()
                .bodyToMono(BriefDTO.class)
                .timeout(Duration.ofSeconds(5))
                .doOnError(error -> log.error("Error fetching brief with id {}: {}", briefId, error.getMessage()))
                .onErrorResume(error -> {
                    log.warn("Brief service unavailable for id {}", briefId);
                    return Mono.empty();
                });
    }
}
