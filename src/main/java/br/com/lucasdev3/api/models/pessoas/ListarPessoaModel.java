package br.com.lucasdev3.api.models.pessoas;

import br.com.lucasdev3.api.domain.Endereco;
import br.com.lucasdev3.api.domain.Pessoa;
import br.com.lucasdev3.api.models.GenericModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListarPessoaModel extends GenericModel {

  private Long id;

  private String nome;

  private String dataNascimento;

  private List<Endereco> enderecos;

  public ListarPessoaModel(Pessoa pessoa) {
    this.id = pessoa.getId();
    this.nome = pessoa.getNome();
    this.dataNascimento = pessoa.getDataNascimento();
    this.enderecos = pessoa.getEnderecos();

  }
}
