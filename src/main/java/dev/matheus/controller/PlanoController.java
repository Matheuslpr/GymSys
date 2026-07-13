package dev.matheus.controller;

import dev.matheus.doc.PlanoControllerDoc;
import dev.matheus.dto.PlanoRequest;
import dev.matheus.dto.PlanoResponse;
import dev.matheus.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planos")
public class PlanoController implements PlanoControllerDoc {

    private final PlanoService service;

    public PlanoController(PlanoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanoResponse cadastrar(@RequestBody @Valid PlanoRequest request) {
        return service.cadastrar(request);
    }

    @GetMapping
    public Page<PlanoResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public PlanoResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PlanoResponse atualizar(@PathVariable Long id, @RequestBody @Valid PlanoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.excluir(id);
    }
}