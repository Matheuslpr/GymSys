package dev.matheus.dto;

public record GraduacaoResponse(
        Long id,
        String nome,
        Long modalidadeId
) {

    public static GraduacaoResponse fromEntity(dev.matheus.model.Graduacao graduacao) {
        return new GraduacaoResponse(
                graduacao.getId(),
                graduacao.getNome(),
                graduacao.getModalidade() != null ? graduacao.getModalidade().getId() : null
        );
    }
}
