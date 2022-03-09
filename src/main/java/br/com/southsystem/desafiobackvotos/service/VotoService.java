package br.com.southsystem.desafiobackvotos.service;

import br.com.southsystem.desafiobackvotos.model.Voto;
import br.com.southsystem.desafiobackvotos.view.VotoCommand;

public interface VotoService {
    Voto votar(VotoCommand command);
}
