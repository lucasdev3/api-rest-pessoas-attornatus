package br.com.lucasdev3.api.models;

import static br.com.lucasdev3.api.utils.DateUtils.dateNow;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseModel<T> extends GenericModel {

  private final String date = dateNow();

  private T data;

  public ResponseModel(T data) {
    this.data = data;
  }
}
