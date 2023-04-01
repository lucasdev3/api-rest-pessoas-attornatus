package br.com.lucasdev3.api.models;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorDetails {

  private final Integer status;

  private final Object message;

  public ErrorDetails(Integer status, String message) {

    super();

    this.status = status;

    this.message = message;

  }

  public ErrorDetails(Integer status, Map<String, List<String>> errors) {
    super();

    this.status = status;

    this.message = errors;

  }
}
