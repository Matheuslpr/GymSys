package dev.matheus.doc;

import dev.matheus.dto.HistoricoGraduacaoResponse;
import dev.matheus.dto.MatriculaModalidadeRequest;
import dev.matheus.dto.MatriculaModalidadeResponse;
import dev.matheus.exception.ErroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(
        name = "Matrículas/Modalidades.",
        description = "Operações para vincular alunos matriculados a modalidades, planos e graduações."
)
public interface MatriculaModalidadeControllerDoc {

    @Operation(
            summary = "Cadastrar vínculo matrícula-modalidade.",
            description = "Vincula uma matrícula a uma modalidade, graduação e plano.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Vínculo cadastrado com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaModalidadeResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar o vínculo.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MatriculaModalidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Vínculo válido.",
                                    value = """
                                        {
                                              "dataInicio": "2026-07-09",
                                              "dataFim": null,
                                              "matriculaId": 1,
                                              "modalidadeId": 1,
                                              "graduacaoId": 1,
                                              "planoId": 1
                                        }
                                        """
                            ))
            )
            MatriculaModalidadeRequest request
    );

    @Operation(
            summary = "Listar vínculos matrícula-modalidade.",
            description = "Lista os vínculos entre matrículas e modalidades de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.")
            }
    )
    Page<MatriculaModalidadeResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar vínculo por ID.",
            description = "Retorna os dados de um vínculo matrícula-modalidade específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vínculo encontrado."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Vínculo não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaModalidadeResponse buscarPorId(
            @Parameter(description = "ID do vínculo matrícula-modalidade.", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Listar histórico de graduações.",
            description = "Lista o histórico de alterações de graduação de um vínculo matrícula-modalidade.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Vínculo não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    List<HistoricoGraduacaoResponse> listarHistoricoGraduacoes(
            @Parameter(description = "ID do vínculo matrícula-modalidade.", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar vínculo matrícula-modalidade.",
            description = "Atualiza os dados de um vínculo matrícula-modalidade existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Vínculo atualizado com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou vínculo não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaModalidadeResponse atualizar(
            @Parameter(description = "ID do vínculo matrícula-modalidade.", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados do vínculo.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MatriculaModalidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Vínculo válido",
                                    value = """
                                        {
                                              "dataInicio": "2026-07-09",
                                              "dataFim": "2026-12-31",
                                              "matriculaId": 1,
                                              "modalidadeId": 1,
                                              "graduacaoId": 2,
                                              "planoId": 1
                                        }
                                        """
                            ))
            )
            MatriculaModalidadeRequest request
    );

    @Operation(
            summary = "Excluir vínculo matrícula-modalidade.",
            description = "Remove um vínculo matrícula-modalidade pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Vínculo excluído com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Vínculo não encontrado",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID do vínculo matrícula-modalidade.", example = "1", required = true)
            Long id
    );
}
