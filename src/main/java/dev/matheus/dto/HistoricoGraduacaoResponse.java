package dev.matheus.dto;

import dev.matheus.model.HistoricoGraduacao;

import java.time.LocalDateTime;

public record HistoricoGraduacaoResponse(
        Long id,
        Long graduacaoId,
        LocalDateTime dataAlteracao
) {
    public static HistoricoGraduacaoResponse fromEntity(HistoricoGraduacao h) {
        return new HistoricoGraduacaoResponse(
                h.getId(),
                h.getGraduacao() != null ? h.getGraduacao().getId() : null,
                h.getDataAlteracao()
        );
    }
}
