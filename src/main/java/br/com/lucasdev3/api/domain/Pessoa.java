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
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PESSOAS")
@Getter
@Setter
@NoArgsConstructor
public class Pessoa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome", unique = true)
  private String nome;

  @Column(name = "data_nascimento")
  private String dataNascimento;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Endereco> enderecos = new ArrayList<>();

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private String dataCriacao;

  @Column(name = "data_atualizacao")
  private String dataAtualizacao;


  public Pessoa(SalvarPessoaModel dto) {
    this.nome = dto.getNome();
    this.dataNascimento = dto.getDataNascimento();
    this.enderecos = dto.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList());
    this.dataCriacao = dateNow();
    this.dataAtualizacao = dateNow();
  }

  public void update(SalvarPessoaModel dto) {
    this.nome = dto.getNome();
    this.dataNascimento = dto.getDataNascimento();
    this.enderecos = dto.getEnderecos().stream().map(Endereco::new).collect(Collectors.toList());
    this.enderecos.forEach(endereco -> {
      endereco.setDataAtualizacao(dateNow());
    });
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
