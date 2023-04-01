package br.com.lucasdev3.api.domain;

import br.com.lucasdev3.api.models.endereco.SalvarEnderecoModel;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Endereco implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "logradouro não pode ser nulo")
  private String logradouro;

  @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "cep não obedece ao padrao xxxxx-xxx")
  @NotBlank(message = "cep não pode ser nulo")
  private String cep;

  @NotBlank(message = "numero não pode ser nulo")

  @Pattern(regexp = "^\\d{1,5}$", message = "numero não obedece ao padrao determinado. Deve estar entre 1 e 99999")
  private String numero;

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private String dataCriacao;

  @Column(name = "data_atualizacao", nullable = false)
  private String dataAtualizacao;

  public Endereco(SalvarEnderecoModel salvarEnderecoModel) {
    this.logradouro = salvarEnderecoModel.getLogradouro();
    this.cep = salvarEnderecoModel.getCep();
    this.numero = salvarEnderecoModel.getNumero();
    this.dataCriacao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date());
    this.dataAtualizacao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date());
  }

}
