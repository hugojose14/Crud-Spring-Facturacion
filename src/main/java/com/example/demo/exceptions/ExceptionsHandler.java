package com.example.demo.exceptions;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice 
public class ExceptionsHandler {
	
private static final Logger LOG= Logger.getLogger(ExceptionsHandler.class.getName());
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorCode handleExeption(Exception e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(UUID.randomUUID().toString());
		ec.setMensaje("El error no fue encontrado");
		logError(ec, e);

		
		return ec;
	}
	
	@ExceptionHandler(RegistroNoEncontradoExeception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorCode registroNoEncontrado(RegistroNoEncontradoExeception e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		
		return ec;
		
	}
	
	@ExceptionHandler(ValorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorCode negativoExeception(ValorException e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		
		return ec;
		
	}
	
	@ExceptionHandler(AlcanceValorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorCode rangoExeception(AlcanceValorException e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		
		return ec;
		
	}
	
	@ExceptionHandler(ValoresCaracterException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorCode caracteresExeception(ValoresCaracterException e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		
		return ec;
		
	}
	
	@ExceptionHandler(NombreIncorrectoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorCode nombreExeception(NombreIncorrectoException e) {
		ErrorCode ec= new ErrorCode();
		ec.setCodigo(this.getId());
		ec.setMensaje(e.getMessage());
		logError(ec, e);
		
		return ec;
		
	}
	private void logError(ErrorCode ec,Exception e) {
		LOG.severe(ec.getCodigo());
		LOG.severe(ec.getMensaje());
		LOG.severe(ec.getCodigo());
		LOG.severe(e.getMessage());
		
		
	}
	
	public String getId() {
		
		final String uuid = UUID.randomUUID().toString().replace("-", "");
		
		return uuid;
	}

}
