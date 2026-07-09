package dev.matheus.dto;

import dev.matheus.model.Graduacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record GraduacaoRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve conter no máximo 100 caracteres.")
        String nome,
        @NotNull(message = "A modalidade é obrigatória.")
        Long modalidadeId
) {

    public Graduacao toEntity(){
        Graduacao graduacao = new Graduacao();
        preencher(graduacao);
        return graduacao;
    }

    public void preencher(Graduacao graduacao) {
        graduacao.setNome(this.nome);
    }
}
