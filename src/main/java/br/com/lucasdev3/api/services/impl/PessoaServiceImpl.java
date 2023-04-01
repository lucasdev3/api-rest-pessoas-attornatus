package br.com.lucasdev3.api.services.impl;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.exceptions.NotFoundException;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import br.com.lucasdev3.api.models.pessoas.ListarPessoaModel;
import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import br.com.lucasdev3.api.repositories.PessoaRepository;
import br.com.lucasdev3.api.services.PessoaService;
import br.com.lucasdev3.api.services.ValidacaoPessoaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

  @Autowired
  private final PessoaRepository pessoaRepository;

  @Autowired
  private final List<ValidacaoPessoaService> validacaoPessoaServiceList;

  public PessoaServiceImpl(PessoaRepository pessoaRepository,
      List<ValidacaoPessoaService> validacaoPessoaServiceList) {
    this.pessoaRepository = pessoaRepository;
    this.validacaoPessoaServiceList = validacaoPessoaServiceList;
  }


  private static final String NOT_FOUND_MESSAGE = "Nenhuma pessoa encontrada!";

  @Override
  public List<ListarPessoaModel> buscar() {
    List<Pessoa> pessoas = this.pessoaRepository.findAll();
    if (pessoas.isEmpty()) {
      throw new NotFoundException(NOT_FOUND_MESSAGE);
    }
    return pessoas.stream().map(ListarPessoaModel::new).collect(Collectors.toList());
  }

  @Override
  public ListarPessoaModel buscaPorId(Long id) {
    Pessoa pessoa = getPessoa(id);
    return new ListarPessoaModel(pessoa);
  }

  @Override
  public void salvar(SalvarPessoaModel salvarPessoaModel) {
    validacaoPessoaServiceList.forEach(v -> {
      v.validacao(pessoaRepository, salvarPessoaModel);
    });
    Pessoa pessoa = new Pessoa(salvarPessoaModel);
    this.pessoaRepository.save(pessoa);
  }

  @Override
  public void atualizar(SalvarPessoaModel salvarPessoaModel, Long id) {
    validacaoPessoaServiceList.forEach(v -> {
      v.validacao(pessoaRepository, salvarPessoaModel);
    });
    Pessoa pessoa = getPessoa(id);
    pessoa.update(salvarPessoaModel);
    this.pessoaRepository.save(pessoa);
  }

  @Override
  public void adicionarEndereco(SalvarEnderecoModel salvarEnderecoModel, Long id) {
    Pessoa pessoa = getPessoa(id);
    pessoa.getEnderecos().add(new Endereco(salvarEnderecoModel));
    this.pessoaRepository.save(pessoa);
  }

  public void deletar(Long id) {
    Pessoa pessoa = getPessoa(id);
    pessoaRepository.delete(pessoa);
  }

  private Pessoa getPessoa(Long id) {
    return this.pessoaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE));
  }

}
