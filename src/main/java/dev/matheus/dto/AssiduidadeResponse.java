package dev.matheus.dto;

import java.time.LocalDateTime;

public record AssiduidadeResponse(
        Long id,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida,
        Long matriculaId


) {
    public static AssiduidadeResponse fromEntity(dev.matheus.model.Assiduidade assiduidade) {
        return new AssiduidadeResponse(
                assiduidade.getId(),
                assiduidade.getDataEntrada(),
                assiduidade.getDataSaida(),
                assiduidade.getMatricula() != null ? assiduidade.getMatricula().getId() : null
        );
    }
}