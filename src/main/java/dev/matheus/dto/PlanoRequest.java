package dev.matheus.dto;

import dev.matheus.model.Plano;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlanoRequest (
        @NotNull(message = "O status ativo é obrigatório.")
        Boolean ativo,

        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve conter no máximo 100 caracteres.")
        String nome,

        @NotNull(message = "O valor mensal é obrigatório.")
        @Positive(message = "O valor mensal deve ser maior que zero.")
        BigDecimal valorMensal,

        @NotNull(message = "A modalidade é obrigatória.")
        Long modalidadeId
){
    public Plano toEntity(){
        Plano plano = new Plano();
        preencher(plano);
        return plano;
    }

    public void preencher(Plano plano) {
        plano.setAtivo(this.ativo);
        plano.setNome(this.nome);
        plano.setValorMensal(this.valorMensal);
    }
}
