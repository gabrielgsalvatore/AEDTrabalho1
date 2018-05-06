package br.furb.aed.trabalho.exception;

import br.furb.aed.trabalho.Calculadora;

/**
 * ErroSintaxeExpressao tem como objetivo definir a exceção de erro de sintaxe na expressão passada para a {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class ErroSintaxeExpressao extends RuntimeException {

	private static final long serialVersionUID = 6920515479740496093L;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param termoLeitura
	 */
	public ErroSintaxeExpressao(String termoLeitura) {
		super("Erro de sintaxe [" + termoLeitura + "]");
	}

}
