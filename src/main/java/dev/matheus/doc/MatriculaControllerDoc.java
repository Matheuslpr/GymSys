package dev.matheus.doc;

import dev.matheus.dto.MatriculaRequest;
import dev.matheus.dto.MatriculaResponse;
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

@Tag(
        name = "Matrículas",
        description = "Operações para cadastro, consulta, atualização e exclusão de matrículas."
)
public interface MatriculaControllerDoc {

    @Operation(
            summary = "Cadastrar matrícula",
            description = "Cria uma nova matrícula vinculada a um aluno.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Matrícula cadastrada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar a matrícula.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MatriculaRequest.class),
                            examples = @ExampleObject(
                                    name = "Matrícula válida",
                                    value = """
                                        {
                                              "dataMatricula": "2026-07-09",
                                              "dataVencimento": 10,
                                              "dataEncerramento": null,
                                              "status": "ATIVA",
                                              "alunoId": 1
                                        }
                                        """
                            ))
            )
            MatriculaRequest request
    );

    @Operation(
            summary = "Listar matrículas.",
            description = "Lista as matrículas de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso.")
            }
    )
    Page<MatriculaResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar matrícula por ID.",
            description = "Retorna os dados de uma matrícula específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matrícula encontrada."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Matrícula não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaResponse buscarPorId(
            @Parameter(description = "ID da matrícula", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar matrícula.",
            description = "Atualiza os dados de uma matrícula existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou matrícula não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    MatriculaResponse atualizar(
            @Parameter(description = "ID da matrícula", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da matrícula.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MatriculaRequest.class),
                            examples = @ExampleObject(
                                    name = "Matrícula válida.",
                                    value = """
                                        {
                                              "dataMatricula": "2026-07-09",
                                              "dataVencimento": 10,
                                              "dataEncerramento": "2026-12-31",
                                              "status": "ENCERRADA",
                                              "alunoId": 1
                                        }
                                        """
                            ))
            )
            MatriculaRequest request
    );

    @Operation(
            summary = "Excluir matrícula.",
            description = "Remove uma matrícula pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Matrícula excluída com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Matrícula não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID da matrícula", example = "1", required = true)
            Long id
    );
}