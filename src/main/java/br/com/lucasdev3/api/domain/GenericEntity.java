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
import javax.persistence.MappedSuperclass;
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

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class GenericEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "data_criacao", nullable = false, updatable = false)
  private String dataCriacao = dateNow();

  @Column(name = "data_atualizacao", nullable = false)
  private String dataAtualizacao = dateNow();


  @PostPersist
  public void postPersist() {
    this.dataCriacao = dateNow();
    this.dataAtualizacao = dateNow();
  }

  @PostUpdate
  public void postUpdate() {
    this.dataAtualizacao = dateNow();
  }

}
