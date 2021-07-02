package com.mukesh.ms.myappointment.handler;

import com.mukesh.ms.myappointment.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HealthServiceExceptionHandler {
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({ResourceNotFoundException.class})
  @ResponseBody
  public String handleResourceNotFound(ResourceNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({ConstraintViolationException.class})
  @ResponseBody
  public List<String> handleConstraintViolationException(ConstraintViolationException ex) {
    return ex.getConstraintViolations().stream().map(cv -> cv.getMessage()).collect(Collectors.toList());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({PSQLException.class})
  @ResponseBody
  public String handlePSQLException(PSQLException ex) {
    return ex.getServerErrorMessage().getDetail();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseBody
  public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> errList = ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
    return errList;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({EntityNotFoundException.class})
  @ResponseBody
  public String handleEntityNotFoundException(EntityNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class})
  @ResponseBody
  public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({DataIntegrityViolationException.class})
  @ResponseBody
  public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    Throwable e = fetchPSQLExceptionIfAny(ex, 4);
    return e.getMessage();
  }

  /**
   * check n (counter) levels to fetch psql exception if any
   *
   * @param e
   * @return
   */
  private Throwable fetchPSQLExceptionIfAny(Throwable e, int counter) {
    if (e instanceof PSQLException || counter == 0) {
      return e;
    } else if (e.getCause() != null) {
      return fetchPSQLExceptionIfAny(e.getCause(), --counter);
    }
    return e;
  }
}
