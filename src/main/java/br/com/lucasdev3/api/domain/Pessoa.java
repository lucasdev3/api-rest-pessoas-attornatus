package br.com.lucasdev3.api.domain;

import static br.com.lucasdev3.api.utils.DateUtils.dateNow;

import br.com.lucasdev3.api.models.pessoas.SalvarPessoaModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PESSOAS")
@Getter
@Setter
@NoArgsConstructor
public class Pessoa extends GenericEntity{

  @Column(name = "nome", unique = true)
  @NotBlank(message = "nome é obrigatorio")
  private String nome;

  @Column(name = "data_nascimento")
  @NotBlank(message = "data de nascimento é obrigatorio")
  private String dataNascimento;

  @OneToMany(cascade = CascadeType.ALL)
  @NotNull(message = "endereco é obrigatorio")
  @Valid
  private List<Endereco> enderecos = new ArrayList<>();

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private String dataCriacao = dateNow();

  @Column(name = "data_atualizacao", nullable = false)
  private String dataAtualizacao = dateNow();
  ;


  public Pessoa(SalvarPessoaModel dto) {
    this.nome = dto.getNome();
    this.dataNascimento = dto.getDataNascimento();
    this.enderecos = dto.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList());
  }

  public void update(SalvarPessoaModel dto) {
    this.nome = dto.getNome();
    this.dataNascimento = dto.getDataNascimento();
    this.enderecos = dto.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList());
  }

  @PostPersist
  public void postPersist() {
    System.out.println("Data de criação atualizada - Pessoas");
    this.dataCriacao = dateNow();
  }

  @PostUpdate
  public void postUpdate() {
    System.out.println("Data de atualização atualizada - Pessoas");
    this.dataAtualizacao = dateNow();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pessoa pessoa = (Pessoa) o;
    return Objects.equals(enderecos, pessoa.enderecos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enderecos);
  }

}
