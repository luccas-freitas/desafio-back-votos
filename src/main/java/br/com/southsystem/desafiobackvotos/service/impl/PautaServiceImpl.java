package br.com.southsystem.desafiobackvotos.service.impl;

import br.com.southsystem.desafiobackvotos.model.Pauta;
import br.com.southsystem.desafiobackvotos.model.types.VotoType;
import br.com.southsystem.desafiobackvotos.repository.PautaRepository;
import br.com.southsystem.desafiobackvotos.repository.VotoRepository;
import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class PautaServiceImpl implements PautaService {
    private final PautaRepository repository;
    private final VotoRepository votoRepository;

    public PautaServiceImpl(PautaRepository repository, VotoRepository votoRepository) {
        this.repository = repository;
        this.votoRepository = votoRepository;
    }

    @Override
    public void open(PautaCommand command) {
        Pauta pauta = repository.save(Pauta.from(command));
        this.run(pauta);
    }

    //Nova consulta para recuperar lista de votos
    private void close(Long id) {
        repository.save(
                repository.findById(id).map(pauta -> {
                    pauta.setQtdSim(this.contarVotos(pauta, VotoType.SIM));
                    pauta.setQtdNao(this.contarVotos(pauta, VotoType.NAO));
                    pauta.setDataFim(LocalDateTime.now());

                    return pauta;
                }).orElseThrow()
        );
    }

    @Override
    public Pauta find(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Pauta não cadastrada"));
    }

    @Async
    @Override
    public void run(Pauta pauta) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.schedule(() ->
                this.close(pauta.getId()),
                pauta.getTempoSessao(),
                TimeUnit.MINUTES
        );

        scheduler.shutdown();
    }

    private Integer contarVotos(Pauta pauta, VotoType votoType) {
        return votoRepository.countByIdPautaIdAndVotoType(pauta.getId(), votoType);
    }
}
