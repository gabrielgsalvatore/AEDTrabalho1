package br.furb.aed.trabalho;

import br.furb.aed.trabalho.comparator.OperadorComparator;
import br.furb.aed.trabalho.exception.ErroSintaxeExpressao;
import br.furb.aed.trabalho.fila.Fila;
import br.furb.aed.trabalho.listaEstatica.ListaEstatica;
import br.furb.aed.trabalho.pilha.Pilha;
import br.furb.aed.trabalho.pilha.PilhaVetor;
import br.furb.aed.trabalho.util.Util;

/**
 * CalculadoraEtapaC tem como objetivo definir a classe de calculo da etapa C da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class CalculadoraEtapaC extends CalculadoraEtapaBase {

	private final Fila<String> exprInfixada;
	private final int tamanhoPilha;
	
	/**
	 * Construtor padrão
	 * 
	 * @param exprInfixada
	 * @param tamanhoPilha
	 */
	public CalculadoraEtapaC(Fila<String> exprInfixada, int tamanhoPilha) {
		super();
		this.exprInfixada = exprInfixada;
		this.tamanhoPilha = tamanhoPilha;
	}

	/**
	 * Processa uma {@link Fila} de expressão infixada em uma fila de expressão posfixada
	 * 
	 * @param exprInfixada
	 * @return {@link Fila} de expressão posfixada
	 */
	public Fila<String> processar() {
		ListaEstatica<String> expressaoPosfixada = new ListaEstatica<>();
		Pilha<String> pilhaAux = new PilhaVetor<>(tamanhoPilha);
		
		String termoLeitura = Calculadora.TERMO_VAZIO;
		while (!exprInfixada.estaVazia()) {
			termoLeitura = exprInfixada.retirar();
			
			if (ehOperando(termoLeitura)) {
				
				expressaoPosfixada.inserir(termoLeitura);
				
			} else if (ehParenteseAbertura(termoLeitura)) {
				
				pilhaAux.push(termoLeitura);
				
			} else if (ehParenteseFechamento(termoLeitura)) {
				
				processarParenteseFechamento(expressaoPosfixada, pilhaAux, termoLeitura);
				
			} else if (ehOperador(termoLeitura)) {
				
				if (pesoOperadorMaiorOuIgual(termoLeitura, pilhaAux)) {
					expressaoPosfixada.inserir(pilhaAux.pop());
				}
				
				pilhaAux.push(termoLeitura);
			}
		}
		
		esvaziarPilha(expressaoPosfixada, pilhaAux);
		
		return Util.converterListaEmFila(expressaoPosfixada);
	}

	/**
	 * Adiciona todos os elementos da {@link Pilha} na {@link ListaEstatica}
	 * 
	 * @param listaEstatica
	 * @param pilha
	 */
	private void esvaziarPilha(ListaEstatica<String> listaEstatica, Pilha<String> pilha) {
		while (!pilha.estaVazia()) {
			listaEstatica.inserir(pilha.pop());
		}
	}
	
	/**
	 * Verifica se o peso do operador é maior ou igual ao item do topo da pilha
	 * 
	 * @param operador
	 * @param pilha
	 * @return
	 */
	private boolean pesoOperadorMaiorOuIgual(String operador, Pilha<String> pilha) {
		if (!pilha.estaVazia()) {
			String ultimoDadoPilha = pilha.peek();
			if (String.valueOf(Calculadora.PARENTESE_ABERTURA).equals(ultimoDadoPilha) || String.valueOf(Calculadora.PARENTESE_FECHAMENTO).equals(ultimoDadoPilha)) {
				return false;
			}
			
			int peso = new OperadorComparator().compare(operador, ultimoDadoPilha);
			
			return 0 >= peso;
		}
		return false;
	}
	
	/**
	 * Processa o fechamento de um parentese. Desempilha os termos da pilha auxiliar (passada por parâmetro) e adiciona na lista de expressão posfixada até que a pilha esteja vazia ou encontre um parentese de abertura
	 * 
	 * @param expressaoPosfixada
	 * @param pilhaAux
	 * @param termoLeitura
	 */
	private void processarParenteseFechamento(ListaEstatica<String> expressaoPosfixada, Pilha<String> pilhaAux, String termoLeitura) {
		if (pilhaAux.estaVazia()) {
			throw new ErroSintaxeExpressao(termoLeitura);
		}
		
		String leituraPilha = Calculadora.TERMO_VAZIO;
		while (!pilhaAux.estaVazia()) {
			leituraPilha = pilhaAux.pop();
			
			if (String.valueOf(Calculadora.PARENTESE_ABERTURA).equals(leituraPilha)) {
				break;
			}
			
			expressaoPosfixada.inserir(leituraPilha);
		}
	}

}
