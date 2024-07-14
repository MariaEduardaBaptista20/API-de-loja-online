package com.store.store.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ProdutoException.class)
	protected ResponseEntity<?> handleEmailException(ProdutoException ex){
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
	}

}
