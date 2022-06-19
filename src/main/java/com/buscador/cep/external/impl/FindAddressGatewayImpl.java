package com.buscador.cep.external.impl;

import com.buscador.cep.core.dtos.AddressDTO;
import com.buscador.cep.core.exceptions.GatewayException;
import com.buscador.cep.external.FindAddressGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class FindAddressGatewayImpl implements FindAddressGateway {

    @Value("${api.uri}")
    private String apiURI;

    @Override
    @Cacheable(value = "Address", key = "#cep")
    public AddressDTO find(final String cep) {
        var client = HttpClient.newBuilder().build();
        try {
            var uri = buildURI(cep);

            log.info("[FindAddressGatewayImpl] sending request to URI: {}", uri);

            var request = buildRequest(uri);

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            validateResponse(response.statusCode());

            return new ObjectMapper().readValue(response.body(), AddressDTO.class);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new GatewayException(ex.getMessage());
        }
    }

    private URI buildURI(final String cep) {
        return new DefaultUriBuilderFactory(apiURI).builder()
                .build(cep);
    }

    private HttpRequest buildRequest(URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .build();
    }

    private void validateResponse(final int statusCode) {
        if (statusCode != HttpStatus.OK.value()) {
            throw new GatewayException("Falha ao buscar endereço na API externa");
        }
    }
}
