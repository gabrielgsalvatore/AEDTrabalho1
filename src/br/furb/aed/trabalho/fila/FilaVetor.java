package br.furb.aed.trabalho.fila;

public class FilaVetor<T> implements Fila<T> {

	private static int ZERO = 0;
	private static int UM = 1;
	
	private T[] info;
	private int limite;
	private int tamanho;
	private int inicio;
	
	@SuppressWarnings("unchecked")
	public FilaVetor(int limite) {
		this.limite = limite;
		this.info = (T[]) new Object[limite];
		this.inicio = ZERO;
		this.tamanho = ZERO;
	}
	
	public void inserir(T valor) {
		if (tamanho == limite) {
			throw new FilaCheiaException();
		}
		
		int fim = (this.inicio + this.tamanho) % this.limite;
		this.info[fim] = valor;
		this.tamanho++;
	}
	
	public boolean estaVazia() {
		return ZERO == this.tamanho;
	}
	
	public T peek() {
		if (estaVazia()) {
			throw new FilaVaziaException();
		}
		return this.info[this.inicio];
	}
	
	public T retirar() {
		T valor = this.peek();
		
		this.info[this.inicio] = null;
		this.inicio = (this.inicio + UM) % this.limite;
		tamanho--;
		
		return valor;
	}
	
	public void liberar() {
		while (!estaVazia()) {
			retirar();
		}
	}
	
//	public FilaVetor<T> criarFilaConcatenada(FilaVetor<T> f2) {
//		
//	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int indice = this.inicio;
		for (int idxLoop = ZERO; idxLoop < this.tamanho; idxLoop++) {
			if (ZERO < idxLoop) {
				sb.append(",");
			}
			sb.append(this.info[indice]);
			indice = (indice + UM) % this.limite;
		}
		
		return sb.toString();
	}
}
