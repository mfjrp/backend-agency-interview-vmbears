package br.com.domain.agency.repository;

import br.com.domain.agency.model.Geracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeracaoRepository extends JpaRepository<Geracao, Long> {
}
