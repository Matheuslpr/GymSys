package dev.matheus.dto;

public record ModalidadeResponse(
        Long id,
        String nome,
        Boolean ativa
) {

    public static ModalidadeResponse fromEntity(dev.matheus.model.Modalidade modalidade) {
        return new ModalidadeResponse(
                modalidade.getId(),
                modalidade.getNome(),
                modalidade.getAtiva()
        );
    }
}
