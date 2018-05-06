package br.furb.aed.trabalho.pilha;

public interface Pilha<T> {

	void push(T dado);
	
	T pop();
	
	T peek();
	
	boolean estaVazia();
	
	void liberar();
}
