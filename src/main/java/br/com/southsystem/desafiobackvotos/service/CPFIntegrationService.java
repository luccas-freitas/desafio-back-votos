package br.com.southsystem.desafiobackvotos.service;

import br.com.southsystem.desafiobackvotos.view.CPFCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpfService", url = "${integration.cpf.baseUrl}")
public interface CPFIntegrationService {
    @GetMapping("/{cpf}")
    ResponseEntity<CPFCommand> verify(@PathVariable String cpf);
}
