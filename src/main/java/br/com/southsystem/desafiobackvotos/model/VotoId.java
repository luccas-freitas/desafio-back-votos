package br.com.southsystem.desafiobackvotos.model;

import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class VotoId implements Serializable {
    private static final long serialVersionUID = -212977213486570526L;

    @ManyToOne
    private Pauta pauta;
    private String associado;

    public static VotoId from(VotoCommand command, Pauta pauta) {
        VotoId id = new VotoId();
        id.pauta = pauta;
        id.associado = command.getAssociado();

        return id;
    }
}
