package br.com.southsystem.desafiobackvotos.controller;

import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/pauta")
public class PautaController {
    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody(required = false) PautaCommand command) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.open(command));
        } catch (Exception e) {
            log.error("Erro ao criar Pauta: {}", e.getMessage(), e);

            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
}
