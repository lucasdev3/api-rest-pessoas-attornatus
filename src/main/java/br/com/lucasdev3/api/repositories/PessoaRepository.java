package br.com.lucasdev3.api.repositories;

import br.com.lucasdev3.api.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  Boolean existsByNome(String nome);

}
