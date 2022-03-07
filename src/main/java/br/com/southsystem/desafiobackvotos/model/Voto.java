package br.com.southsystem.desafiobackvotos.model;

import br.com.southsystem.desafiobackvotos.model.types.VotoType;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Voto implements Serializable {
    private static final long serialVersionUID = 1917214529233028819L;

    @EmbeddedId
    private VotoId id;

    @Enumerated(EnumType.STRING)
    private VotoType votoType;

    public static Voto from(VotoCommand command, Pauta pauta) {
        Voto voto = new Voto();
        voto.id = VotoId.from(command, pauta);
        voto.votoType = command.getVotoType();

        return voto;
    }
}
