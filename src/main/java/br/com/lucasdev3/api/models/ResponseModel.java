package br.com.lucasdev3.api.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseModel extends GenericModel {

  private final String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date());

  private Object data;

  public ResponseModel(Object data) {
    this.data = data;
  }
}
