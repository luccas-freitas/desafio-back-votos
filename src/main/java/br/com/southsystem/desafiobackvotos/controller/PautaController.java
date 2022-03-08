package br.com.southsystem.desafiobackvotos.controller;

import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@Slf4j
@RestController
@RequestMapping("/api/pauta")
public class PautaController {
    private final PautaService service;

    public PautaController(PautaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar nova Pauta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",
            description = "Pauta criada com sucesso",
                content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PautaCommand.class))
        }),
        @ApiResponse(responseCode = "500",
            description = "Erro ao criar pauta",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<?> create(@Parameter(description = "Tempo de sessão da Pauta em minutos (não-obrigatório)")
                                    @RequestBody(required = false) PautaCommand command) {
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
