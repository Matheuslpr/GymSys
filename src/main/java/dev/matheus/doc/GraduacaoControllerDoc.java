package dev.matheus.doc;

import dev.matheus.dto.GraduacaoRequest;
import dev.matheus.dto.GraduacaoResponse;
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
        name = "Graduações",
        description = "Operações para cadastro, consulta, atualização e exclusão de graduações."
)
public interface GraduacaoControllerDoc {

    @Operation(
            summary = "Cadastrar graduação.",
            description = "Cria uma nova graduação vinculada a uma modalidade.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Graduação cadastrada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou regra de negócio.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    GraduacaoResponse cadastrar(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados necessários para cadastrar a graduação.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GraduacaoRequest.class),
                            examples = @ExampleObject(
                                    name = "Graduação válida",
                                    value = """
                                        {
                                              "nome": "Faixa Branca",
                                              "modalidadeId": 1
                                        }
                                        """
                            ))
            )
            GraduacaoRequest request
    );

    @Operation(
            summary = "Listar graduações.",
            description = "Lista as graduações de forma paginada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de graduações retornada com sucesso.")
            }
    )
    Page<GraduacaoResponse> listar(
            @Parameter(description = "Informações de paginação e ordenação.")
            Pageable pageable
    );

    @Operation(
            summary = "Buscar graduação por ID.",
            description = "Retorna os dados de uma graduação específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Graduação encontrada."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Graduação não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    GraduacaoResponse buscarPorId(
            @Parameter(description = "ID da graduação", example = "1", required = true)
            Long id
    );

    @Operation(
            summary = "Atualizar graduação.",
            description = "Atualiza os dados de uma graduação existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Graduação atualizada com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação, regra de negócio ou graduação não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    GraduacaoResponse atualizar(
            @Parameter(description = "ID da graduação", example = "1", required = true)
            Long id,

            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados atualizados da graduação.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = GraduacaoRequest.class),
                            examples = @ExampleObject(
                                    name = "Graduação válida.",
                                    value = """
                                        {
                                              "nome": "Faixa Azul",
                                              "modalidadeId": 1
                                        }
                                        """
                            ))
            )
            GraduacaoRequest request
    );

    @Operation(
            summary = "Excluir graduação.",
            description = "Remove uma graduação pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Graduação excluída com sucesso."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Graduação não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErroResponse.class))
                    )
            }
    )
    void deletar(
            @Parameter(description = "ID da graduação", example = "1", required = true)
            Long id
    );
}