package dev.matheus.dto;

import dev.matheus.model.FaturaMatricula;
import dev.matheus.model.enums.StatusFatura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FaturaMatriculaRequest(
        @NotNull(message = "A data de vencimento é obrigatória.")
        LocalDate dataVencimento,

        @NotNull(message = "O valor é obrigatório.")
        @Positive(message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        LocalDateTime dataPagamento,

        LocalDate dataCancelamento,

        StatusFatura status,

        @NotNull(message = "A matrícula é obrigatória.")
        Long matriculaId
) {
    public FaturaMatricula toEntity(){
        FaturaMatricula faturaMatricula = new FaturaMatricula();
        preencher(faturaMatricula);
        return faturaMatricula;
    }

    public void preencher (FaturaMatricula faturaMatricula) {
        faturaMatricula.setDataVencimento(this.dataVencimento);
        faturaMatricula.setValor(this.valor);
        faturaMatricula.setDataPagamento(this.dataPagamento);
        faturaMatricula.setDataCancelamento(this.dataCancelamento);
        if (status != null) {
            faturaMatricula.setStatus(this.status);
        }
    }
}
