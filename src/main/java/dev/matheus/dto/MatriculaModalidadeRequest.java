package dev.matheus.dto;

import dev.matheus.model.MatriculaModalidade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MatriculaModalidadeRequest(
        LocalDate dataInicio,
        LocalDate dataFim,

        @NotNull(message = "A matrícula é obrigatória.")
        Long matriculaId,

        @NotNull(message = "A modalidade é obrigatória.")
        Long modalidadeId,

        @NotNull(message = "A graduação é obrigatória.")
        Long graduacaoId,

        @NotNull(message = "O plano é obrigatório.")
        Long planoId
) {

    public MatriculaModalidade toEntity(){
        MatriculaModalidade matriculaModalidade = new MatriculaModalidade();
        preencher(matriculaModalidade);
        return matriculaModalidade;
    }

    public void preencher(MatriculaModalidade matriculaModalidade) {
        matriculaModalidade.setDataInicio(dataInicio);
        matriculaModalidade.setDataFim(dataFim);
    }
}
