package br.com.dio.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ProductStoreFrontSaveDTO(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("name")
        String name,
        @JsonProperty("active")
        Boolean active) {
}
