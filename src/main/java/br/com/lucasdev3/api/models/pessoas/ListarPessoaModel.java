package br.com.lucasdev3.api.models.pessoas;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.GenericModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ListarPessoaModel extends GenericModel {

  private Long id;

  private String nome;

  private String dataNascimento;

  private List<Endereco> enderecos;

  private String dataCriacao;

  private String dataAtualizacao;

  public ListarPessoaModel(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.nome = pessoa.getNome();
    this.dataNascimento = pessoa.getDataNascimento();
    this.enderecos = pessoa.getEnderecos();
    this.dataCriacao = pessoa.getDataCriacao();
    this.dataAtualizacao = pessoa.getDataAtualizacao();
  }
}
