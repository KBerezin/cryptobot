package com.bootybanger.cryptobot.common.integration.client;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;

public abstract class BaseClient {
    private final WebClient webClient;

    public BaseClient() {
        webClient = WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .build();
    }

    public <R> Mono<R> getClient(String uri, Map<String, String> params,
                                    Map<String, String> headers, @NotNull Class<R> responseType, String ... pathVariables) {

        return webClient.get()
                .uri(uri, uriBuilder -> buildQuery(uriBuilder, params, pathVariables))
                .headers(cons -> cons.setAll(headers))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <R> Mono<R> getClient(String uri, Map<String, String> params,
                                 Map<String, String> headers, @NotNull ParameterizedTypeReference<R> responseType,
                                 String ... pathVariables) {

        return webClient.get()
                .uri(uri, uriBuilder -> buildQuery(uriBuilder, params, pathVariables))
                .headers(cons -> cons.setAll(headers))
                .retrieve()
                .bodyToMono(responseType);
    }

    //TODO может не нужен
    public <R, B> Mono<R> postClient(String uri, @NotNull B body, Map<String, String> params,
                                 Map<String, String> headers, @NotNull Class<R> responseType, String ... pathVariables) {
        return webClient.post()
                .uri(uri, uriBuilder -> buildQuery(uriBuilder, params, pathVariables))
                .headers(cons -> cons.setAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <R, B> Mono<R> postClient(String uri, @NotNull B body, Map<String, String> params,
                                     Map<String, String> headers, @NotNull ParameterizedTypeReference<R> responseType,
                                     String ... pathVariables) {
        return webClient.post()
                .uri(uri, uriBuilder -> buildQuery(uriBuilder, params, pathVariables))
                .headers(cons -> cons.setAll(headers))
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }

    private URI buildQuery(UriBuilder uriBuilder, Map<String, String> params, String ... pathVariables) {
        Arrays.stream(pathVariables).forEach(uriBuilder::path);
        params.forEach(uriBuilder::queryParam);
        return uriBuilder.build();
    }
}

