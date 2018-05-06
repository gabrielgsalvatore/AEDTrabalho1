package br.furb.aed.trabalho.facade;

import br.furb.aed.trabalho.Calculadora;
import br.furb.aed.trabalho.fila.Fila;

/**
 * CalculadoraFacade tem como objetivo simplificar a utilização da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class CalculadoraFacade {
	
	private final Calculadora calculadora = new Calculadora();

	/**
	 * Recebe uma expressão e retorna o resultado da expressão
	 * 
	 * @param expressao
	 * @return
	 */
	public double calcularExpressao(String expressao) {
		Fila<String> termos = calculadora.extrairTermos(expressao);
		Fila<String> expressaoPosfixada = calculadora.gerarExprPosfixada(termos);
		
		return calculadora.calcularExprPosfixada(expressaoPosfixada);
	}
}
