package br.com.southsystem.desafiobackvotos.view;

import br.com.southsystem.desafiobackvotos.model.types.VotoType;
import lombok.Data;

@Data
public class VotoCommand {
    private Long pautaId;
    private String associado;
    private VotoType votoType;
}
