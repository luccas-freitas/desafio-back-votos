package br.com.southsystem.desafiobackvotos.repository;

import br.com.southsystem.desafiobackvotos.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
