package dev.matheus.doc;

import dev.matheus.dto.PlanoRequest;
import dev.matheus.dto.PlanoResponse;
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
        name = "Planos.",
        description = "Operações para cadastro, consulta, atualização e exclusão de planos."
)
public interface PlanoControllerDoc {

    @Operation(
            summary = "Cadastrar plano.",
            description = "Cria um novo plano vinculado a uma modalidade.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plano cadastrado com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    PlanoResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar o plano.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlanoRequest.class),
                            examples = @ExampleObject(
                                    name = "Plano válido",
                                    value = """
                                        {
                                              "ativo": true,
                                              "nome": "Plano Mensal",
                                              "valorMensal": 150.00,
                                              "modalidadeId": 1
                                        }
                                        """
                            ))
            )
            PlanoRequest request
    );

    @Operation(
            summary = "Listar planos.",
            description = "Lista os planos de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de planos retornada com sucesso.")
            }
    )
    Page<PlanoResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar plano por ID.",
            description = "Retorna os dados de um plano específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plano encontrado."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Plano não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    PlanoResponse buscarPorId(
            @Parameter(description = "ID do plano", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar plano.",
            description = "Atualiza os dados de um plano existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou plano não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    PlanoResponse atualizar(
            @Parameter(description = "ID do plano", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados do plano.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PlanoRequest.class))
            )
            PlanoRequest request
    );

    @Operation(
            summary = "Excluir plano.",
            description = "Remove um plano pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Plano excluído com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Plano não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID do plano", example = "1", required = true)
            Long id
    );
}