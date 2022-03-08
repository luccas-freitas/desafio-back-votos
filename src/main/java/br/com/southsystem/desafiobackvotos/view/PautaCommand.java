package br.com.southsystem.desafiobackvotos.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PautaCommand {
    @Schema(example = "10", description = "Tempo em minutos")
    private Integer tempoSessao;
}
