package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationRequest {
    private String model;
    private List<Map<String, String>> messages;
    private Double temperature;
    private Integer maxTokens;

    public static TranslationRequest create(String userPrompt) {
        List<Map<String, String>> messages = List.of(
                Map.of("role", "system", "content", "당신은 도움이 되는 AI 어시스턴트입니다."),
                Map.of("role", "user", "content", userPrompt)
        );
        return new TranslationRequest("openrouter/openai/gpt-4o-mini", messages, 0.7, 150);
    }
}



