package br.com.southsystem.desafiobackvotos.repository;

import br.com.southsystem.desafiobackvotos.model.Voto;
import br.com.southsystem.desafiobackvotos.model.types.VotoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByIdPautaIdAndIdAssociado(Long pautaId, String associado);
    int countByIdPautaIdAndVotoType(Long pautaId, VotoType votoType);
}
