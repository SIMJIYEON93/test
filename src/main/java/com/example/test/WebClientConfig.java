package com.example.test;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;

@Configuration
@PropertySource("classpath:application.properties")
public class WebClientConfig {

        private final Logger logger = LoggerFactory.getLogger(WebClientConfig.class);

        @Value("${openrouter.api.url:}")
        private String openrouterApiUrl;

        @Bean(name = "openRouter")
        public WebClient webClient() {
            return WebClient.builder()
                    .baseUrl(openrouterApiUrl)
                    .defaultHeader("Content-Type", "application/json")
                    .build();
        }
}
