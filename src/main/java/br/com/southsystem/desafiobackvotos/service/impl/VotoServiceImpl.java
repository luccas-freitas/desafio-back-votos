package br.com.southsystem.desafiobackvotos.service.impl;

import br.com.southsystem.desafiobackvotos.model.Pauta;
import br.com.southsystem.desafiobackvotos.model.Voto;
import br.com.southsystem.desafiobackvotos.repository.VotoRepository;
import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.service.VotoService;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {
    private final VotoRepository repository;
    private final PautaService pautaService;

    public VotoServiceImpl(VotoRepository repository, PautaService pautaService) {
        this.repository = repository;
        this.pautaService = pautaService;
    }

    @Override
    public void votar(VotoCommand command) throws Exception {
        Pauta pauta = pautaService.find(command.getPautaId());

        if (this.podeVotar(command, pauta)) {
            repository.save(Voto.from(command, pauta));
        } else {
            throw new Exception("Associado já votou ou a pauta já foi encerrada.");
        }
    }

    // Evitar votos duplicados e em pautas encerradas
    private boolean podeVotar(VotoCommand command, Pauta pauta) {
        return !repository.existsByIdPautaIdAndIdAssociado(command.getPautaId(), command.getAssociado()) &&
                pauta.getDataFim() == null;
    }

}
