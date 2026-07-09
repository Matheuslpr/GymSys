package dev.matheus.dto;

import java.time.LocalDate;

public record MatriculaModalidadeResponse(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        Long matriculaId,
        Long modalidadeId,
        Long graduacaoId,
        Long planoId
) {

    public static MatriculaModalidadeResponse fromEntity(dev.matheus.model.MatriculaModalidade matriculaModalidade) {
        return new MatriculaModalidadeResponse(
                matriculaModalidade.getId(),
                matriculaModalidade.getDataInicio(),
                matriculaModalidade.getDataFim(),
                matriculaModalidade.getMatricula() != null ? matriculaModalidade.getMatricula().getId() : null,
                matriculaModalidade.getModalidade() != null ? matriculaModalidade.getModalidade().getId() : null,
                matriculaModalidade.getGraduacao() != null ? matriculaModalidade.getGraduacao().getId() : null,
                matriculaModalidade.getPlano() != null ? matriculaModalidade.getPlano().getId() : null
        );
    }
}
