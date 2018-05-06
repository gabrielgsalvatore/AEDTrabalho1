package br.furb.aed.trabalho;

import br.furb.aed.trabalho.exception.ErroSintaxeExpressao;
import br.furb.aed.trabalho.fila.Fila;
import br.furb.aed.trabalho.pilha.Pilha;
import br.furb.aed.trabalho.pilha.PilhaVetor;

/**
 * CalculadoraEtapaD tem como objetivo definir a classe de calculo da etapa D da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class CalculadoraEtapaD extends CalculadoraEtapaBase {

	private final Fila<String> exprPosfixada;
	private final int tamanhoPilha;
	
	/**
	 * Construtor padrão
	 * 
	 * @param exprPosfixada
	 * @param tamanhoPilha
	 */
	public CalculadoraEtapaD(Fila<String> exprPosfixada, int tamanhoPilha) {
		super();
		this.exprPosfixada = exprPosfixada;
		this.tamanhoPilha = tamanhoPilha;
	}
	
	/**
	 * Processa a {@link Fila} de expressão posfixada passada no construtor e retorna o resultado da expressão
	 * 
	 * @return
	 */
	public double processar() {
		Pilha<String> pilha = new PilhaVetor<>(tamanhoPilha);
		
		String termoLeitura;
		while (!exprPosfixada.estaVazia()) {
			termoLeitura = exprPosfixada.retirar();
			
			if (ehOperando(termoLeitura)) {
				pilha.push(termoLeitura);
			} else {
				processarOperador(pilha, termoLeitura);
			}
		}
		return retirarDadoDaPilha(pilha);
	}

	/** 
	 * Processa o operador passado por parâmetro utilizando os dois elementos do topo da pilha e adiciona o resultado na pilha
	 * 
	 * @param pilha
	 * @param termoLeitura
	 */
	private void processarOperador(Pilha<String> pilha, String operador) {
		Double valor2 = retirarDadoDaPilha(pilha);
		Double valor1 = retirarDadoDaPilha(pilha);
		Double resultado = null;
		
		if (Operador.ADICAO.getString().equals(operador)) {
			resultado = valor1 + valor2;
		} else if (Operador.SUBTRACAO.getString().equals(operador)) {
			resultado = valor1 - valor2;
		} else if (Operador.MULTIPLICACAO.getString().equals(operador)) {
			resultado = valor1 * valor2;
		} else if (Operador.DIVISAO.getString().equals(operador)) {
			resultado = valor1 / valor2;
		}
		
		adicionarDadoNaPilha(pilha, resultado);
	}

	/**
	 * Adiciona um dado do tipo {@link Double} na pilha
	 * 
	 * @param pilha
	 * @param resultado
	 */
	private void adicionarDadoNaPilha(Pilha<String> pilha, Double resultado) {
		String dado = String.valueOf(resultado).replace(".", ",");
		pilha.push(dado);
	}

	/**
	 * Retira um dado da pilha e converte em um {@link Double}
	 * 
	 * @param pilha
	 * @return
	 */
	private Double retirarDadoDaPilha(Pilha<String> pilha) {
		if (!pilha.estaVazia()) {
			return Double.valueOf(pilha.pop().replaceAll(",", "."));
		}
		
		throw new ErroSintaxeExpressao("");
	}

}
