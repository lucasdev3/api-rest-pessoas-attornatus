package br.com.lucasdev3.api.models.pessoas;

import br.com.lucasdev3.api.models.GenericModel;
import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalvarPessoaModel extends GenericModel {

  private String nome;

  private String dataNascimento;

  private List<SalvarEnderecoModel> enderecos;

}
