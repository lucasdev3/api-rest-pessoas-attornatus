package br.com.lucasdev3.api.models;

import static br.com.lucasdev3.api.utils.DateUtils.dateNow;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseModel extends GenericModel {

  private final String date = dateNow();

  private Object data;

  public ResponseModel(Object data) {
    this.data = data;
  }
}
