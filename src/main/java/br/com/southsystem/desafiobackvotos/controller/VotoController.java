package br.com.southsystem.desafiobackvotos.controller;

import br.com.southsystem.desafiobackvotos.service.VotoService;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/voto")
public class VotoController {
    private final VotoService service;

    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> votar(@RequestBody VotoCommand command) {
        try {
            service.votar(command);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Erro ao criar Voto: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
