package dev.matheus.controller;

import dev.matheus.doc.ModalidadeControllerDoc;
import dev.matheus.dto.ModalidadeRequest;
import dev.matheus.dto.ModalidadeResponse;
import dev.matheus.service.ModalidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modalidades")
public class ModalidadeController implements ModalidadeControllerDoc {

    private final ModalidadeService service;

    public ModalidadeController(ModalidadeService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModalidadeResponse cadastrar(@RequestBody @Valid ModalidadeRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<ModalidadeResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public ModalidadeResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ModalidadeResponse atualizar(@PathVariable Long id, @RequestBody @Valid ModalidadeRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}
