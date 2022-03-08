package br.com.southsystem.desafiobackvotos.service;

import br.com.southsystem.desafiobackvotos.model.Pauta;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import org.springframework.scheduling.annotation.Async;

public interface PautaService {
    Pauta open(PautaCommand command);
    Pauta find(Long id) throws Exception;
    @Async void run(Pauta pauta);
}
