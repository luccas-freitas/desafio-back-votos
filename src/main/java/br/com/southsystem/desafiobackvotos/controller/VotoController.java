package br.com.southsystem.desafiobackvotos.controller;

import br.com.southsystem.desafiobackvotos.service.VotoService;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import io.swagger.v3.oas.annotations.Operation;
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

@Slf4j
@RestController
@RequestMapping("/api/voto")
public class VotoController {
    private final VotoService service;

    public VotoController(VotoService service) {
        this.service = service;
    }

    @Operation(summary = "Votar em Pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Pauta votada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VotoCommand.class))
                    }),
            @ApiResponse(responseCode = "500",
                    description = "Erro ao votar em pauta",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<?> votar(@RequestBody VotoCommand command) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.votar(command));
        } catch (Exception e) {
            log.error("Erro ao criar Voto: {}", e.getMessage(), e);

            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
}
