package br.furb.aed.trabalho;

/**
 * Operador tem como objetivo definir os Operadores
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public enum Operador {
	
	ADICAO('+'),
	SUBTRACAO('-'),
	MULTIPLICACAO('*'),
	DIVISAO('/');
	
	private char code;
	
	Operador(char code) {
		this.code = code;
	}
	
	public char getChar() {
		return code;
	}
	
	public String getString() {
		return String.valueOf(code);
	}
}
