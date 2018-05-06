package br.furb.aed.trabalho;

import br.furb.aed.trabalho.util.Util;

/**
 * CalculadoraEtapaBase tem por objetivo definir a base das etapas da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public abstract class CalculadoraEtapaBase {

	/**
	 * Verifica se a {@link String} passada é um parentese de fechamento
	 * 
	 * @param termoLeitura
	 * @return
	 */
	protected boolean ehParenteseFechamento(String termoLeitura) {
		return String.valueOf(Calculadora.PARENTESE_FECHAMENTO).equals(termoLeitura);
	}

	/**
	 * Verifica se a {@link String} passada é um parentese de abertura
	 * 
	 * @param termoLeitura
	 * @return
	 */
	protected boolean ehParenteseAbertura(String termoLeitura) {
		return String.valueOf(Calculadora.PARENTESE_ABERTURA).equals(termoLeitura);
	}

	/**
	 * Verifica se a {@link String} passada é um operando
	 * 
	 * @param termo
	 * @return 
	 */
	protected boolean ehOperando(String termo) {
		char charLeitura = termo.charAt(0);
		
		if (Operador.SUBTRACAO.getChar() == charLeitura) {
			if (2 > termo.length()) {
				return false;
			}
			charLeitura = termo.charAt(1);
		}
		
		return Util.contemElementoLista(Calculadora.NUMEROS, String.valueOf(charLeitura));
	}
	
	/**
	 * Verifica se a {@link String} passada é um operador
	 * 
	 * @param termo
	 * @return
	 */
	protected boolean ehOperador(String termo) {
		return Util.contemElementoLista(Calculadora.OPERADORES, termo);
	}
}
