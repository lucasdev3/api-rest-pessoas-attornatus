package br.com.lucasdev3.api.domain;

import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ENDERECOS")
@Getter
@Setter
@NoArgsConstructor
public class Endereco extends GenericEntity {

  @NotBlank(message = "logradouro não pode ser nulo")
  private String logradouro;

  @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "cep não obedece ao padrao xxxxx-xxx")
  @NotBlank(message = "cep não pode ser nulo")
  private String cep;

  @NotBlank(message = "numero não pode ser nulo")

  @Pattern(regexp = "^\\d{1,5}$", message = "numero não obedece ao padrao determinado. Deve estar entre 1 e 99999")
  private String numero;

  @Column(name = "enderecoPrincipal")
  private Boolean enderecoPrincipal = false;

  public Endereco(SalvarEnderecoModel salvarEnderecoModel) {
    this.logradouro = salvarEnderecoModel.getLogradouro();
    this.cep = salvarEnderecoModel.getCep();
    this.numero = salvarEnderecoModel.getNumero();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Endereco endereco = (Endereco) o;
    return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(cep,
        endereco.cep) && Objects.equals(numero, endereco.numero)
        && Objects.equals(enderecoPrincipal, endereco.enderecoPrincipal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logradouro, cep, numero, enderecoPrincipal);
  }

}
