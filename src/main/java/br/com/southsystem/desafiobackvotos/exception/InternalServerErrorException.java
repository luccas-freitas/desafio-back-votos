package br.com.southsystem.desafiobackvotos.exception;

public class InternalServerErrorException extends RuntimeException {
	public InternalServerErrorException(String message) {
        super(message);
    }
}
