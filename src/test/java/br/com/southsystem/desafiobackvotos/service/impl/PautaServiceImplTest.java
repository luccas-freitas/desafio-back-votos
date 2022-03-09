package br.com.southsystem.desafiobackvotos.service.impl;

import br.com.southsystem.desafiobackvotos.DesafioBackVotosApplicationTests;
import br.com.southsystem.desafiobackvotos.exception.InternalServerErrorException;
import br.com.southsystem.desafiobackvotos.model.Pauta;
import br.com.southsystem.desafiobackvotos.service.PautaService;
import br.com.southsystem.desafiobackvotos.view.PautaCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PautaServiceImplTest extends DesafioBackVotosApplicationTests {
    @Autowired
    private PautaService service;

    @Test
    public void deveCriarPautaSemParametrosERetornarOk() {
        Assertions.assertNotNull(service.open(null));
    }

    @Test
    public void deveCriarPautaComParametrosERetornarOk() {
        Assertions.assertNotNull(service.open(PautaCommand.from(10)));
    }

    @Test
    public void deveBuscarPautaPeloIdERetornarOk() {
        try {
            Pauta pauta = service.open(null);
            Assertions.assertEquals(pauta, service.find(pauta.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deveBuscarPautaPeloIdERetornarErro() {
        Assertions.assertThrows(InternalServerErrorException.class, () -> service.find(999L));
    }
}
