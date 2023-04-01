package br.com.lucasdev3.api.services.validations;

import br.com.lucasdev3.api.exceptions.NameInUseException;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import org.springframework.stereotype.Component;

@Component("ExistePessoaPeloNomeValidacao")
public class ExistePessoaPeloNomeValidacaoImpl implements ValidacaoPessoaService {

  @Override
  public void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel) {
    if (pessoaRepository.existsByNome(salvarPessoaModel.getNome())) {
      throw new NameInUseException("Nome já está sendo utilizado!");
    }
  }

}
