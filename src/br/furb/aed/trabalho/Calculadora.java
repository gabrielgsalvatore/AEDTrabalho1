package br.furb.aed.trabalho;

import java.util.Arrays;

import br.furb.aed.trabalho.fila.Fila;
import br.furb.aed.trabalho.listaEstatica.ListaEstatica;

/**
 * Calculadora tem como objetivo receber uma expressao matemática e retornar o seu resultado
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class Calculadora {
	
	public static final ListaEstatica<String> NUMEROS = definirListaNumeros();
	public static final ListaEstatica<String> OPERADORES = definirOperadores();
	
	public static final String TERMO_VAZIO = "";
	public static final char PARENTESE_ABERTURA = '(';
	public static final char PARENTESE_FECHAMENTO = ')';
	public static final char SIMBOLO_DECIMAL = ',';
	
	
	private int tamanhoPilha = 1000;
	
	/**
	 * Extrai os termos da expressão passada por parâmetro em uma {@link Fila} de expressão infixada
	 * 
	 * @param expressao
	 * @return
	 */
	public Fila<String> extrairTermos(String expressao) {
		tamanhoPilha = expressao.length();
		
		CalculadoraEtapaB calculadoraEtapaB = new CalculadoraEtapaB(expressao);
		return calculadoraEtapaB.processar();
	}
	
	/**
	 * Gera uma {@link Fila} de expresão posfixada a partir de uma {@link Fila} de expressão infixada
	 * 
	 * @param exprInfixada
	 * @return
	 */
	public Fila<String> gerarExprPosfixada(Fila<String> exprInfixada) {
		CalculadoraEtapaC calculadoraEtapaC = new CalculadoraEtapaC(exprInfixada, tamanhoPilha);
		return calculadoraEtapaC.processar();
	}
	
	/**
	 * Realiza o calculo de uma {@link Fila} de expressão posfixada
	 * 
	 * @param exprPosfixada
	 * @return
	 */
	public double calcularExprPosfixada(Fila<String> exprPosfixada) {
		CalculadoraEtapaD calculadoraEtapaD = new CalculadoraEtapaD(exprPosfixada, tamanhoPilha);
		return calculadoraEtapaD.processar();
	}
	
	/**
	 * Retorna uma {@link ListaEstatica} de {@link String} com os números
	 * 
	 * @return
	 */
	private static ListaEstatica<String> definirListaNumeros() {
		ListaEstatica<String> lista = new ListaEstatica<>();
		
		lista.inserir("0");
		lista.inserir("1");
		lista.inserir("2");
		lista.inserir("3");
		lista.inserir("4");
		lista.inserir("5");
		lista.inserir("6");
		lista.inserir("7");
		lista.inserir("8");
		lista.inserir("9");
		
		return lista; 
	}
	
	/**
	 * Retorna uma {@link ListaEstatica} de {@link String} com os itens do {@link Operador}
	 * 
	 * @return
	 */
	private static ListaEstatica<String> definirOperadores() {
		ListaEstatica<String> lista = new ListaEstatica<>();
		
		Arrays.stream(Operador.values()).forEach(operador -> lista.inserir(operador.getString()));
		
		return lista;
	}
}
