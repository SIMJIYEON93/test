package com.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
public class AiApiService {

    private static final Logger logger = LoggerFactory.getLogger(AiApiService.class);
    private final WebClient webClient;

    public AiApiService(@Qualifier("openRouter") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> translate(String prompt) {
        TranslationRequest request = TranslationRequest.create(prompt);

        return webClient.post()
                .uri("/api/v1/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> {
                    System.out.println("Response: " + response);
                })
                .doOnError(error -> {
                    System.err.println("Error: " + error.getMessage());
                });
    }

}
