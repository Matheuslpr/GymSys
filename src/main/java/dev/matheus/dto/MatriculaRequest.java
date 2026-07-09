package dev.matheus.dto;

import dev.matheus.model.Matricula;
import dev.matheus.model.enums.StatusMatricula;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MatriculaRequest(
        @PastOrPresent(message = "A data da matrícula não pode estar no futuro.")
        LocalDate dataMatricula,

        @NotNull(message = "O dia de vencimento é obrigatório.")
        @Min(value = 1, message = "O dia de vencimento deve ser entre 1 e 31.")
        @Max(value = 31, message = "O dia de vencimento deve ser entre 1 e 31.")
        Integer dataVencimento,

        LocalDate dataEncerramento,

        StatusMatricula status,

        @NotNull(message = "O aluno é obrigatório.")
        Long alunoId

) {

    public Matricula toEntity() {
        Matricula matricula = new Matricula();
        preencher(matricula);
        return matricula;
    }

    public void preencher(Matricula matricula) {
        matricula.setDataMatricula(dataMatricula);
        matricula.setDataVencimento(dataVencimento);
        matricula.setDataEncerramento(dataEncerramento);
        if (status != null) {
            matricula.setStatus(status);
        }
    }
}
