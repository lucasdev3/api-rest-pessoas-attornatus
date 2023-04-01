package br.com.lucasdev3.api.services;

import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.ListarPessoaModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.util.List;
import javax.validation.Valid;

public interface PessoaService {

  public List<ListarPessoaModel> buscar();

  public ListarPessoaModel buscaPorId(Long id);

  public void salvar(SalvarPessoaModel salvarPessoaModel);

  public void atualizar(SalvarPessoaModel salvarPessoaModel, Long id);

  public void adicionarEndereco(@Valid SalvarEnderecoModel salvarEnderecoModel, Long id);

}
