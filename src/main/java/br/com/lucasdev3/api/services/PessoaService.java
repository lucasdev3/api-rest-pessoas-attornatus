package br.com.lucasdev3.api.services;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.models.GenericModel;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.ListarPessoaModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.util.List;
import java.util.Map;

public interface PessoaService {

  List<ListarPessoaModel> buscar();

  ListarPessoaModel buscaPorId(Long id);

  Map<String, List<Endereco>> buscaEnderecos(Long pessoaId);

  void salvar(SalvarPessoaModel salvarPessoaModel);

  void atualizar(SalvarPessoaModel salvarPessoaModel, Long id);

  void adicionarEndereco(SalvarEnderecoModel salvarEnderecoModel, Long id);

  void definirEnderecoPrincipal(Long pessoaId, Long enderecoId);

  void deletar(Long id);

}
