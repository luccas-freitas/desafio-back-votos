package br.com.southsystem.desafiobackvotos.model;

import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Pauta implements Serializable {
    private static final long serialVersionUID = 262815133667703885L;
    private static final int DEFAULT_TIME = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInicio;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFim;

    private Integer tempoSessao;

    private Integer qtdSim;

    private Integer qtdNao;

    public static Pauta from(PautaCommand command) {
        Pauta pauta = new Pauta();
        pauta.dataInicio = LocalDateTime.now();
        pauta.tempoSessao = command != null && command.getTempoSessao() != null ?
            command.getTempoSessao() : DEFAULT_TIME;

        return pauta;
    }
}
