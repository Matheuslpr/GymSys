package dev.matheus.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FaturasEmAbertoProjection {

    Long getMatriculasId();
    String getAlunoNome();
    LocalDate getDataVencimento();
    BigDecimal getValor();
}
