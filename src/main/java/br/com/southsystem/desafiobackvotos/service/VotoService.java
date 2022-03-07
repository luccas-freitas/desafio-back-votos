package br.com.southsystem.desafiobackvotos.service;

import br.com.southsystem.desafiobackvotos.view.VotoCommand;

public interface VotoService {
    void votar(VotoCommand command) throws Exception;
}
