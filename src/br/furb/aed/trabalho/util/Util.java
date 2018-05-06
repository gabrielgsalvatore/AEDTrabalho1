package br.furb.aed.trabalho.util;

import br.furb.aed.trabalho.fila.Fila;
import br.furb.aed.trabalho.fila.FilaVetor;
import br.furb.aed.trabalho.listaEstatica.ListaEstatica;

/**
 * Util tem por objetivo definir a classe de métodos utilitários.
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class Util {

	/**
	 * Converte uma {@link ListaEstatica} em uma {@link Fila}. Insere todos os itens da {@link ListaEstatica} em {@link Fila} na mesma ordem
	 * 
	 * @param expressaoPosfixada
	 * @return
	 */
	public static <T> Fila<T> converterListaEmFila(ListaEstatica<T> expressaoPosfixada) {
		Fila<T> fila = new FilaVetor<>(expressaoPosfixada.getTamanho());
		
		T item;
		for (int idx = 0; idx < expressaoPosfixada.getTamanho(); idx++) {
			item = expressaoPosfixada.obterElemento(idx);
			fila.inserir(item);
		}
		
		return fila;
	}

	/**
	 * Verifica se existe um determinado elemento dentro de uma {@link ListaEstatica}
	 * 
	 * @param lista
	 * @param valor
	 * @return
	 */
	public static <T> boolean contemElementoLista(ListaEstatica<T> lista, T valor) {
		return ListaEstatica.RESULTADO_NAO_ENCONTRADO != lista.buscar(valor);
	}
}
