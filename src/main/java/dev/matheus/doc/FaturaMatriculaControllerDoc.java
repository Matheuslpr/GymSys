package dev.matheus.doc;


import dev.matheus.dto.FaturaMatriculaRequest;
import dev.matheus.dto.FaturaMatriculaResponse;
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
        name = "Faturas de Matrícula",
        description = "Operações para cadastro, consulta, atualização e exclusão de faturas de matrícula."
)
public interface FaturaMatriculaControllerDoc {

    @Operation(
            summary = "Cadastrar fatura",
            description = "Cria uma nova fatura vinculada a uma matrícula.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Fatura cadastrada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    FaturaMatriculaResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar a fatura.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FaturaMatriculaRequest.class),
                            examples = @ExampleObject(
                                    name = "Fatura válida",
                                    value = """
                                        {
                                              "dataVencimento": "2026-08-10",
                                              "valor": 199.90,
                                              "dataPagamento": null,
                                              "dataCancelamento": null,
                                              "status": "ABERTA",
                                              "matriculaId": 1
                                        }
                                        """
                            ))
            )
            FaturaMatriculaRequest request
    );

    @Operation(
            summary = "Listar faturas",
            description = "Lista as faturas de matrícula de forma paginada",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de faturas retornada com sucesso.")
            }
    )
    Page<FaturaMatriculaResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar fatura por ID",
            description = "Retorna os dados de uma fatura de matrícula específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Fatura encontrada."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Fatura não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    FaturaMatriculaResponse buscarPorId(
            @Parameter(description = "ID da fatura", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar fatura",
            description = "Atualiza os dados de uma fatura de matrícula existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Fatura atualizada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou fatura não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    FaturaMatriculaResponse atualizar(
            @Parameter(description = "ID da fatura", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da fatura.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FaturaMatriculaRequest.class),
                            examples = @ExampleObject(
                                    name = "Fatura válida",
                                    value = """
                                        {
                                              "dataVencimento": "2026-08-10",
                                              "valor": 199.90,
                                              "dataPagamento": "2026-08-05T14:30:00",
                                              "dataCancelamento": null,
                                              "status": "PAGA",
                                              "matriculaId": 1
                                        }
                                        """
                            ))
            )
            FaturaMatriculaRequest request
    );

    @Operation(
            summary = "Excluir fatura",
            description = "Remove uma fatura de matrícula pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Fatura excluída com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Fatura não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID da fatura", example = "1", required = true)
            Long id
    );
}