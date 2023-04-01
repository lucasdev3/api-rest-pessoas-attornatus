package br.com.lucasdev3.api.exceptions;

import br.com.lucasdev3.api.models.ErrorDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationErrors(
      MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
        getErrorsMap(errors));
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorDetails> constraintViolationException(
      ConstraintViolationException ex) {
    List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
        .collect(
            Collectors.toList());
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
        getErrorsMap(errors));
    return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDetails> notFoundException(NotFoundException ex) {
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NameInUseException.class)
  public ResponseEntity<ErrorDetails> notFoundException(NameInUseException ex) {
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(DataNascimentoException.class)
  public ResponseEntity<ErrorDetails> dataNascimentoException(DataNascimentoException ex) {
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(LimiteEnderecoPrincipalException.class)
  public ResponseEntity<ErrorDetails> dataNascimentoException(LimiteEnderecoPrincipalException ex) {
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(), ex.getMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.NOT_ACCEPTABLE);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
    ErrorDetails errorModel = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getLocalizedMessage());
    return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Map<String, List<String>> getErrorsMap(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>();
    errorResponse.put("errors", errors);
    return errorResponse;
  }

}
