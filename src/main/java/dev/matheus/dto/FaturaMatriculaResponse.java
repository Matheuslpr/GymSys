package dev.matheus.dto;

import dev.matheus.model.enums.StatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FaturaMatriculaResponse(
        Long id,
        LocalDate dataVencimento,
        BigDecimal valor,
        LocalDateTime dataPagamento,
        LocalDate dataCancelamento,
        StatusFatura status,
        Long matriculaId
) {

    public static FaturaMatriculaResponse fromEntity(dev.matheus.model.FaturaMatricula faturaMatricula) {
        return new FaturaMatriculaResponse(
                faturaMatricula.getId(),
                faturaMatricula.getDataVencimento(),
                faturaMatricula.getValor(),
                faturaMatricula.getDataPagamento(),
                faturaMatricula.getDataCancelamento(),
                faturaMatricula.getStatus(),
                faturaMatricula.getMatricula() != null ? faturaMatricula.getMatricula().getId() : null
        );

    }
}
