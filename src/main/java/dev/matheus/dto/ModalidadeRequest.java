package dev.matheus.dto;

import dev.matheus.model.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record ModalidadeRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve conter no máximo 100 caracteres.")
        String nome,
        @NotNull(message = "O status ativo é obrigatório.")
        Boolean ativa
) {
    public Modalidade toEntity() {
        Modalidade modalidade = new Modalidade();
        preencher(modalidade);
        return modalidade;
    }

    public void preencher(Modalidade modalidade) {
        modalidade.setNome(this.nome);
        modalidade.setAtiva(this.ativa);
    }
}
