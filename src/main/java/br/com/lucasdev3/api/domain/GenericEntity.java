package br.com.lucasdev3.api.domain;

import static br.com.lucasdev3.api.utils.DateUtils.dateNow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
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
  @JsonIgnore
  private String dataCriacao = dateNow();

  @Column(name = "data_atualizacao", nullable = false)
  @JsonIgnore
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
