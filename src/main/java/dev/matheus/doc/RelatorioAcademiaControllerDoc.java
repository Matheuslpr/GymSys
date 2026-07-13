package dev.matheus.doc;

import dev.matheus.projection.AlunosPorCidadeProjection;
import dev.matheus.projection.FaturamentoMensalProjection;
import dev.matheus.projection.FaturasEmAbertoProjection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(
        name = "Relatórios",
        description = "Relatórios gerenciais da academia."
)
public interface RelatorioAcademiaControllerDoc {

    @Operation(
            summary = "Faturamento mensal.",
            description = "Retorna o faturamento agregado por mês.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório retornado com sucesso.")
            }
    )
    List<FaturamentoMensalProjection> faturamentoMensal();

    @Operation(
            summary = "Alunos por cidade.",
            description = "Retorna a quantidade de alunos agrupados por cidade.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório retornado com sucesso.")
            }
    )
    List<AlunosPorCidadeProjection> alunosPorCidade();

    @Operation(
            summary = "Faturas em aberto.",
            description = "Retorna as faturas com pagamento pendente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Relatório retornado com sucesso.")
            }
    )
    List<FaturasEmAbertoProjection> faturasEmAbeto();
}
