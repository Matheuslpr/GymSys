package dev.matheus.doc;

import dev.matheus.dto.ModalidadeRequest;
import dev.matheus.dto.ModalidadeResponse;
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
        name = "Modalidades.",
        description = "Operações para cadastro, consulta, atualização e exclusão de modalidades."
)
public interface ModalidadeControllerDoc {

    @Operation(
            summary = "Cadastrar modalidade.",
            description = "Cria uma nova modalidade no sistema de academia.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Modalidade cadastrada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    ModalidadeResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar a modalidade.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ModalidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Modalidade válida.",
                                    value = """
                                        {
                                              "nome": "Jiu-Jitsu",
                                              "ativa": true
                                        }
                                        """
                            ))
            )
            ModalidadeRequest request
    );

    @Operation(
            summary = "Listar modalidades.",
            description = "Lista as modalidades de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de modalidades retornada com sucesso.")
            }
    )
    Page<ModalidadeResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar modalidade por ID.",
            description = "Retorna os dados de uma modalidade específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Modalidade encontrada."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Modalidade não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    ModalidadeResponse buscarPorId(
            @Parameter(description = "ID da modalidade", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar modalidade.",
            description = "Atualiza os dados de uma modalidade existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Modalidade atualizada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou modalidade não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    ModalidadeResponse atualizar(
            @Parameter(description = "ID da modalidade", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da modalidade.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ModalidadeRequest.class),
                            examples = @ExampleObject(
                                    name = "Modalidade válida",
                                    value = """
                                        {
                                              "nome": "Jiu-Jitsu",
                                              "ativa": false
                                        }
                                        """
                            ))
            )
            ModalidadeRequest request
    );

    @Operation(
            summary = "Excluir modalidade.",
            description = "Remove uma modalidade pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Modalidade excluída com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Modalidade não encontrada",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID da modalidade", example = "1", required = true)
            Long id
    );
}
