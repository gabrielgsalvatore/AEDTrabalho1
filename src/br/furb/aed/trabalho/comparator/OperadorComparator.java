package br.furb.aed.trabalho.comparator;

import java.util.Comparator;

import br.furb.aed.trabalho.Operador;

/**
 * OperadorComparator tem como objetivo definir o comparador de {@link Operador}. Este comparador compara as prioridades entre os operadores.
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class OperadorComparator implements Comparator<String> {
	
	@Override
	/**
	 * Realiza a comparação de prioridade entre dois operadores
	 *
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(String operador1, String operador2) {
		return getPeso(operador1) - getPeso(operador2);
	}
	
	/**
	 * Define o peso/prioridade de um operador passado por parâmetro
	 * 
	 * @param operador
	 * @return
	 */
	private int getPeso(String operador) {
		if (Operador.DIVISAO.getString().equals(operador) || Operador.MULTIPLICACAO.getString().equals(operador)) {
			return 1;
		} else if (Operador.ADICAO.getString().equals(operador) || Operador.SUBTRACAO.getString().equals(operador)) {
			return 0;
 		}
		return 2;
	}

}
