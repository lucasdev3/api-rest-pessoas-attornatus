package br.com.lucasdev3.api.exceptions;

public class LimiteEnderecoPrincipalException extends RuntimeException {

  public LimiteEnderecoPrincipalException(String message) {
    super(message);
  }

  public LimiteEnderecoPrincipalException(String message, Throwable cause) {
    super(message, cause);
  }

}
