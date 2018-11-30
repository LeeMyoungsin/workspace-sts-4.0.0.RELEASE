package kr.hsz.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import kr.hsz.exception.ErrorMessage;
import kr.hsz.exception.RuntimeExceptionImpl;

@ControllerAdvice
public class ErrorAdviceController {

	@ExceptionHandler(value = { RuntimeExceptionImpl.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorMessage handleConflict(RuntimeException ex, WebRequest request) {
    	ErrorMessage em = new ErrorMessage();
    	em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    	em.setMessage(ex.getMessage());
        return em;
    }

	
}
