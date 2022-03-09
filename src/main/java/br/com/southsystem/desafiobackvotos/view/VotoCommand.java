package br.com.southsystem.desafiobackvotos.view;

import br.com.southsystem.desafiobackvotos.model.types.VotoType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VotoCommand {
    @Schema(example = "1", description = "ID da Pauta")
    private Long pautaId;

    @Schema(example = "09166819989", description = "CPF do associado")
    private String associado;

    @Schema(example = "SIM", description = "SIM / NAO")
    private VotoType votoType;
    
    public static VotoCommand from(Long pautaId, String associado, VotoType voto) {
        VotoCommand command = new VotoCommand();
        command.pautaId = pautaId;
        command.associado = associado;
        command.votoType = voto;

        return command;
    }
}
