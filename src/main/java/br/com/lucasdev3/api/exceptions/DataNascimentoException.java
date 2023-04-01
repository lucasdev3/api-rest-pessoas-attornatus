package br.com.lucasdev3.api.exceptions;

public class DataNascimentoException extends RuntimeException {

  public DataNascimentoException(String message) {
    super(message);
  }

  public DataNascimentoException(String message, Throwable cause) {
    super(message, cause);
  }

}
