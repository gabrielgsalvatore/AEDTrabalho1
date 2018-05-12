package br.furb.aed.trabalho.exception;

public class ErroNaNException extends RuntimeException {

	private static final long serialVersionUID = 6230506398914748877L;

	public ErroNaNException() {
		super("Oh jovem, você digitou uma expressão que retorna um NaN!");
	}

}
