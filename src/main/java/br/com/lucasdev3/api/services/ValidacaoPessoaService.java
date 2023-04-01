package br.com.lucasdev3.api.services;

import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;

public interface ValidacaoPessoaService extends ValidacaoService<SalvarPessoaModel> {

  void validacao(PessoaRepository pessoaRepository, SalvarPessoaModel salvarPessoaModel);


}
