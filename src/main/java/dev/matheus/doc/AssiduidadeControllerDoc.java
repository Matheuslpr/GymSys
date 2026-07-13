package dev.matheus.doc;

import dev.matheus.dto.AssiduidadeRequest;
import dev.matheus.dto.AssiduidadeResponse;
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
        name = "Assiduidades",
        description = "Operações para registro e consulta de entrada e saída de alunos."
)
public interface AssiduidadeControllerDoc {

    @Operation(
            summary = "Registrar assiduidade.",
            description = "Registra a entrada/saída de um aluno vinculado a uma matrícula.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Assiduidade registrada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    AssiduidadeResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para registrar a assiduidade.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AssiduidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Assiduidade válida.",
                                    value = """
                                        {
                                              "dataEntrada": "2026-07-09T08:00:00",
                                              "dataSaida": "2026-07-09T10:00:00",
                                              "matriculaId": 1
                                        }
                                        """
                            ))
            )
            AssiduidadeRequest request
    );

    @Operation(
            summary = "Listar assiduidades",
            description = "Lista os registros de assiduidade de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de assiduidades retornada com sucesso.")
            }
    )
    Page<AssiduidadeResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar assiduidade por ID",
            description = "Retorna os dados de um registro de assiduidade específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Assiduidade encontrada."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Assiduidade não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    AssiduidadeResponse buscarPorId(
            @Parameter(description = "ID da assiduidade.", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar assiduidade.",
            description = "Atualiza os dados de um registro de assiduidade existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Assiduidade atualizada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou assiduidade não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    AssiduidadeResponse atualizar(
            @Parameter(description = "ID da assiduidade", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da assiduidade.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AssiduidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Assiduidade válida.",
                                    value = """
                                        {
                                              "dataEntrada": "2026-07-09T08:00:00",
                                              "dataSaida": "2026-07-09T10:00:00",
                                              "matriculaId": 1
                                        }
                                        """
                            ))
            )
            AssiduidadeRequest request
    );

    @Operation(
            summary = "Excluir assiduidade",
            description = "Remove um registro de assiduidade pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Assiduidade excluída com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Assiduidade não encontrada",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID da assiduidade ", example = "1", required = true)
            Long id
    );
}