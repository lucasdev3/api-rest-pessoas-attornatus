package br.com.lucasdev3.api.models.endereco;

import br.com.lucasdev3.api.models.GenericModel;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalvarEnderecoModel extends GenericModel {

  @NotBlank(message = "Logradouro é obrigatório")
  private String logradouro;

  @NotBlank(message = "CEP é obrigatório")
  private String cep;

  @NotBlank(message = "Numero é obrigatório")
  private String numero;

  private Boolean enderecoPrincipal = false;

}
