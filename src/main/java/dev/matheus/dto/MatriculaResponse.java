package dev.matheus.dto;

import dev.matheus.model.enums.StatusMatricula;

import java.time.LocalDate;

public record MatriculaResponse(
        Long id,
        LocalDate dataMatricula,
        Integer dataVencimento,
        LocalDate dataEncerramento,
        StatusMatricula status,
        Long alunoId
) {
    public static MatriculaResponse fromEntity(dev.matheus.model.Matricula matricula) {
        return new MatriculaResponse(
                matricula.getId(),
                matricula.getDataMatricula(),
                matricula.getDataVencimento(),
                matricula.getDataEncerramento(),
                matricula.getStatus(),
                matricula.getAluno() != null ? matricula.getAluno().getId() : null
        );
    }
}
