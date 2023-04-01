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

  @NotBlank(message = "nome é obrigatorio")
  private String nome;

  @NotBlank(message = "data de nascimento é obrigatorio")
  @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Data de nascimento inválida. O formato deve ser dd/MM/yyyy")

  private String dataNascimento;

  @NotNull(message = "endereco é obrigatorio")
  @Valid
  private List<SalvarEnderecoModel> enderecos;

}
