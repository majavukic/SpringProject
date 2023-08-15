package com.projekatit355.helper;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  @ExceptionHandler(NePostojiResursException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorPoruka resourceNotFoundException(NePostojiResursException ex, WebRequest request) {

    ErrorPoruka message = new ErrorPoruka(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return message;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorPoruka globalExceptionHandler(Exception ex, WebRequest request) {

    ErrorPoruka message = new ErrorPoruka(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return message;
  }
}

class ErrorPoruka {

  private int statusCode;
  private Date timestamp;
  private String poruka;
  private String description;

  public ErrorPoruka(int statusCode, Date timestamp, String poruka, String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.poruka = poruka;
    this.description = description;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getPoruka() {
    return poruka;
  }

  public String getDescription() {
    return description;
  }
}