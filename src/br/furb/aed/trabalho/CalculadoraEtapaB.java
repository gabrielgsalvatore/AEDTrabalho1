package br.furb.aed.trabalho;

import br.furb.aed.trabalho.exception.ErroSintaxeExpressao;
import br.furb.aed.trabalho.fila.Fila;
import br.furb.aed.trabalho.listaEstatica.ListaEstatica;
import br.furb.aed.trabalho.util.Util;

/**
 * CalculadoraEtapaB tem como objetivo definir a classe de calculo da etapa B da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class CalculadoraEtapaB extends CalculadoraEtapaBase {

	private int idx = 0;
	private String expressao;
	
	/**
	 * Construtor padrão
	 * 
	 * @param expressao
	 */
	public CalculadoraEtapaB(String expressao) {
		super();
		this.expressao = expressao;
	}
	
	/**
	 * Processa a expressão passada no construtor retornando uma {@link Fila} de expressão infixada
	 * 
	 * @return
	 */
	public Fila<String> processar() {
		ListaEstatica<String> termos = new ListaEstatica<>();
		
		char charLeitura;
		String termo = Calculadora.TERMO_VAZIO;
		
		while (expressao.length() > idx) {
			charLeitura = expressao.charAt(idx);
			termo = processarChar(termos, charLeitura, termo);
			idx++;
		}
		
		adicionarTermo(termos, termo);
		
		validarTermosEncontrados(termos);
		
		return Util.converterListaEmFila(termos);
	}

	private void validarTermosEncontrados(ListaEstatica<String> termos) {
		boolean podeSerOperador = false;
		boolean podeSerOperando = true;
		
		String termo;
		for (int idx = 0; idx < termos.getTamanho(); idx++) {
			termo = termos.obterElemento(idx);
			
			if (ehOperando(termo) || termo.contains(String.valueOf(Calculadora.SIMBOLO_DECIMAL))) {
				
				if (!podeSerOperando) {
					throw new ErroSintaxeExpressao(termo);
				}
				
				// Valida se não inicia com ","
				if (termo.contains(String.valueOf(Calculadora.SIMBOLO_DECIMAL)) && 0 >= termo.indexOf(String.valueOf(Calculadora.SIMBOLO_DECIMAL))) {
					throw new ErroSintaxeExpressao(termo);
				}
				
				podeSerOperador = true;
				podeSerOperando = false;
			} else if (ehOperadorOuParentese(termo)) {
				if (ehOperador(termo)) {
					if (!podeSerOperador) {
						throw new ErroSintaxeExpressao(termo);
					}
					podeSerOperando = true;
					podeSerOperador = false;
				}
			}
		}
	}

	/**
	 * Verifica se é um operador ou parentese
	 * 
	 * @param termo
	 * @return
	 */
	private boolean ehOperadorOuParentese(String termo) {
		return 1 == termo.length() && ehOperadorOuParentese(termo.charAt(0));
	}

	/**
	 * Processa o char passado por parâmetro. Valida o char, adiciona nos termos (caso necessário), adiciona ao termo (caso necessário) e devolve o termo atualizado
	 * 
	 * @param termos
	 * @param charLeitura
	 * @param termo
	 * @return
	 */
	private String processarChar(ListaEstatica<String> termos, char charLeitura, String termo) {
		if (ehCharValido(charLeitura)) {
			
			if (ehOperadorOuParentese(charLeitura)) {
				adicionarTermo(termos, termo);
				adicionarTermo(termos, charLeitura);
				
				return Calculadora.TERMO_VAZIO;
			}
			
			termo += charLeitura;
		}
		
		return termo;
	}

	/**
	 * Verifica se é um operador ou parentese
	 * 
	 * @param charLeitura
	 * @return
	 */
	private boolean ehOperadorOuParentese(char charLeitura) {
		return Calculadora.PARENTESE_ABERTURA == charLeitura || Calculadora.PARENTESE_FECHAMENTO == charLeitura || ehOperador(String.valueOf(charLeitura));
	}
	
	/**
	 * Verifica se é um operador
	 * 
	 * @param str
	 * @return
	 */
	@Override
	protected boolean ehOperador(String str) {
		if (Operador.SUBTRACAO.getString().equals(str)) {
			return !proximoEhNumero();
		}
		return super.ehOperador(str);
	}

	/**
	 * Verifica se o próximo caractere da expressão é um número
	 * 
	 * @return
	 */
	private boolean proximoEhNumero() {
		if (idx + 1 < expressao.length()) {
			return Util.contemElementoLista(Calculadora.NUMEROS, String.valueOf(expressao.charAt(idx + 1)));
		}
		return false;
	}

	/**
	 * Verifica se é um char válido
	 * 
	 * @param charLeitura
	 * @return
	 */
	private boolean ehCharValido(char charLeitura) {
		return ehOperadorOuParentese(charLeitura) //
				|| ehNumeroNegativo(charLeitura) //
				|| ehSimboloDecimal(charLeitura) //
				|| Util.contemElementoLista(Calculadora.NUMEROS, String.valueOf(charLeitura));
	}

	/**
	 * Verifica se o char é o simbolo decimal
	 * 
	 * @param charLeitura
	 * @return
	 */
	private boolean ehSimboloDecimal(char charLeitura) {
		return Calculadora.SIMBOLO_DECIMAL == charLeitura;
	}

	/**
	 * Verifica se o número é negativo
	 * 
	 * @param charLeitura
	 * @return
	 */
	private boolean ehNumeroNegativo(char charLeitura) {
		return Operador.SUBTRACAO.getChar() == charLeitura && proximoEhNumero();
	}

	/**
	 * Adiciona um char na {@link ListaEstatica}
	 * 
	 * @param termos
	 * @param charLeitura
	 */
	private void adicionarTermo(ListaEstatica<String> termos, char charLeitura) {
		adicionarTermo(termos, String.valueOf(charLeitura));
	}

	/**
	 * Adiciona uma {@link String} na {@link ListaEstatica}
	 * 
	 * @param termos
	 * @param termo
	 */
	private void adicionarTermo(ListaEstatica<String> termos, String termo) {
		if (!termo.isEmpty()) {
			termos.inserir(termo);
		}
	}

}
