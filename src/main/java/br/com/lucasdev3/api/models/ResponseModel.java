package br.com.lucasdev3.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseModel<T> extends GenericModel {

  private T data;

  public ResponseModel(T data) {
    this.data = data;
  }
}
