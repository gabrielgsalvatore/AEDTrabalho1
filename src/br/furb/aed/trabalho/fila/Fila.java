package br.furb.aed.trabalho.fila;

public interface Fila<T> {

	void inserir(T dado);
	
	boolean estaVazia();
	
	T peek();
	
	T retirar();
	
	void liberar();
}
