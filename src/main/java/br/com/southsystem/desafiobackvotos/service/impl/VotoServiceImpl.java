package br.com.southsystem.desafiobackvotos.service.impl;

import br.com.southsystem.desafiobackvotos.exception.InternalServerErrorException;
import br.com.southsystem.desafiobackvotos.model.Pauta;
import br.com.southsystem.desafiobackvotos.model.Voto;
import br.com.southsystem.desafiobackvotos.model.types.CPFType;
import br.com.southsystem.desafiobackvotos.model.types.ErrorTypes;
import br.com.southsystem.desafiobackvotos.repository.VotoRepository;
import br.com.southsystem.desafiobackvotos.service.CPFIntegrationService;
import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.service.VotoService;
import br.com.southsystem.desafiobackvotos.view.CPFCommand;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;
import org.springframework.stereotype.Service;

@Service
public class VotoServiceImpl implements VotoService {
    private final VotoRepository repository;
    private final PautaService pautaService;
    private final CPFIntegrationService cpfService;

    public VotoServiceImpl(VotoRepository repository, PautaService pautaService, CPFIntegrationService cpfService) {
        this.repository = repository;
        this.pautaService = pautaService;
        this.cpfService = cpfService;
    }

    @Override
    public Voto votar(VotoCommand command) {
        Pauta pauta = pautaService.find(command.getPautaId());
        this.verificarVoto(command, pauta);

        return repository.save(Voto.from(command, pauta));
    }

    /* Verificar se cpf está habilitado para votar;
     * Evitar votos duplicados e
     * Evitar pautas encerradas */
    private void verificarVoto(VotoCommand command, Pauta pauta) {
        this.associadoHabilitado(command);
        this.associadoJaVotou(command);
        this.pautaAberta(pauta);
    }

    private void associadoHabilitado(VotoCommand command) {
        CPFCommand response = cpfService.verify(command.getAssociado()).getBody();
        if (response != null && CPFType.UNABLE_TO_VOTE.equals(response.getStatus())) {
            throw new InternalServerErrorException(ErrorTypes.UNABLE_TO_VOTE.getValue());
        }
    }

    private void associadoJaVotou(VotoCommand command) {
        if (repository.existsByIdPautaIdAndIdAssociado(command.getPautaId(), command.getAssociado())) {
            throw new InternalServerErrorException(ErrorTypes.ASSOCIADO_JA_VOTOU.getValue());
        }
    }

    private void pautaAberta(Pauta pauta) {
        if (pauta.getDataFim() != null) {
            throw new InternalServerErrorException(ErrorTypes.PAUTA_ENCERRADA.getValue());
        }
    }

}
