package com.mukesh.ms.mailservice.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.AuthenticationFailedException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class MailingExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public List<String> getMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("Exception occurred while data validation: ", ex);
    List<String> errList = ex.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
    return errList;
  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String getHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
    log.error("Data sent is not adhering to contract: ", ex);
    return ex.getMessage();
  }

  @ExceptionHandler(AuthenticationFailedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String getAuthenticationFailedException(AuthenticationFailedException ex) {
    log.error("Authentication Failed for user.", ex);
    return ex.getMessage();
  }
}
