package com.example.test;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/openai")
public class AiApiController {
    private final AiApiService aiApiService;

    public AiApiController(AiApiService aiApiService) {
        this.aiApiService = aiApiService;
    }


    @PostMapping("/translate")
    public Mono<String> translate(@RequestBody Map<String, String> requestBody) {
        String prompt = requestBody.get("prompt");
        if (prompt == null || prompt.isEmpty()) {
            throw new IllegalArgumentException("Prompt must not be null or empty.");
        }
        return aiApiService.translate(prompt);
    }
}
