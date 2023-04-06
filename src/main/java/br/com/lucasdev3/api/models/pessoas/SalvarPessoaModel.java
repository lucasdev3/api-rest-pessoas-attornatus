package br.com.lucasdev3.api.models.pessoas;

import br.com.lucasdev3.api.models.GenericModel;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalvarPessoaModel extends GenericModel {

  private String nome;

  private String dataNascimento;

  private List<SalvarEnderecoModel> enderecos;

}
