package br.com.southsystem.desafiobackvotos.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PautaCommand {
    @Schema(example = "10", description = "Tempo em minutos")
    private Integer tempoSessao;

    public static PautaCommand from(int tempoSessao) {
        PautaCommand command = new PautaCommand();
        command.tempoSessao = tempoSessao;

        return command;
    }
}
