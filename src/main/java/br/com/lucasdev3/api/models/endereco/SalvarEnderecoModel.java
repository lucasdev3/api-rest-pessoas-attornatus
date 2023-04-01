package br.com.lucasdev3.api.models.endereco;

import br.com.lucasdev3.api.models.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalvarEnderecoModel extends GenericModel {

  private String logradouro;

  private String cep;

  private String numero;

}
