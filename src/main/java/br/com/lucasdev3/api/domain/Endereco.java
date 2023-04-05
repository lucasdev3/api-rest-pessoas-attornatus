package br.com.lucasdev3.api.domain;

import static br.com.lucasdev3.api.utils.DateUtils.dateNow;

import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private String dataCriacao = dateNow();

  @Column(name = "data_atualizacao", nullable = false)
  private String dataAtualizacao = dateNow();


  public Endereco(SalvarEnderecoModel salvarEnderecoModel) {
    this.logradouro = salvarEnderecoModel.getLogradouro();
    this.cep = salvarEnderecoModel.getCep();
    this.numero = salvarEnderecoModel.getNumero();
  }

  @PrePersist
  public void prePersist() {
    System.out.println("Data de criação atualizada - Endereço");
    this.dataCriacao = dateNow();
  }

  @PreUpdate
  public void preUpdate() {
    System.out.println("Data de atualização atualizada - Endereço");
    this.dataAtualizacao = dateNow();
  }

}
