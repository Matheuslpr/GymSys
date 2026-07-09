package dev.matheus.dto;


import java.math.BigDecimal;

public record PlanoResponse(
        Long id,
        Boolean ativo,
        String nome,
        BigDecimal valorMensal,
        Long modalidadeId
) {

    public static PlanoResponse fromEntity(dev.matheus.model.Plano plano) {
        return new PlanoResponse(
                plano.getId(),
                plano.isAtivo(),
                plano.getNome(),
                plano.getValorMensal(),
                plano.getModalidade() != null ? plano.getModalidade().getId() : null
        );
    }
}
