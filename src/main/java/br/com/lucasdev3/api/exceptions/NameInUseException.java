package br.com.lucasdev3.api.exceptions;

public class NameInUseException extends RuntimeException {

  public NameInUseException(String message) {
    super(message);
  }

  public NameInUseException(String message, Throwable cause) {
    super(message, cause);
  }

}
