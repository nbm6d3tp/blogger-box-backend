package com.dauphine.blogger.controllers.handlers;

import com.dauphine.blogger.exeptions.CategoryAlreadyExistedException;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.exeptions.PostNotFoundByIDException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

  @ExceptionHandler({
      CategoryNotFoundByIDException.class,
      PostNotFoundByIDException.class,
  })
  public ResponseEntity<String> handleNotFoundException(Exception e) {
    logger.warn("[NOT FOUND] {}", e.getMessage());
    return ResponseEntity.status(404).body(e.getMessage());
  }

  @ExceptionHandler({
      CategoryAlreadyExistedException.class,
  })
  public ResponseEntity<String> handleAlreadyExistedException(Exception e) {
    logger.warn("[ALREADY EXISTED] {}", e.getMessage());
    return ResponseEntity.status(400).body(e.getMessage());
  }
}
