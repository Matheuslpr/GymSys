package dev.matheus.dto;

import dev.matheus.model.Assiduidade;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AssiduidadeRequest(
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida,

        @NotNull(message = "A matrícula é obrigatória.")
        Long matriculaId
) {
    public Assiduidade toEntity(){
        Assiduidade assiduidade = new Assiduidade();
        preencher(assiduidade);
        return assiduidade;
    }
    public void preencher(Assiduidade assiduidade){
       assiduidade.setDataEntrada(dataEntrada);
       assiduidade.setDataSaida(dataSaida);
    }
}
