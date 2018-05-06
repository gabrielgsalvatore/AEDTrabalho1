package br.furb.aed.trabalho.pilha;

public class PilhaVetor<T> implements Pilha<T> {

	private static final int ZERO = 0;
	private static final int UM = 1;
	private static final int PILHA_VAZIA = ZERO;
	private static final char VIRGULA = ',';
	
	private T[] info;
	private int limite;
	private int tamanho;
	
	@SuppressWarnings("unchecked")
	public PilhaVetor(int limite) {
		this.limite = limite;
		this.info = (T[]) new Object[this.limite];
	}
	
	@Override
	public void push(T dado) {
		if (this.tamanho == this.limite) {
			throw new PilhaCheiaException();
		}
		this.info[this.tamanho++] = dado;
	}
	
	private void validarTirarPilha() {
		if (estaVazia()) {
			throw new PilhaVaziaException();
		}
	}

	@Override
	public T pop() {
		validarTirarPilha();
		this.tamanho--;
		return info[this.tamanho];
	}

	@Override
	public T peek() {
		validarTirarPilha();
		return info[this.tamanho - UM];
	}

	@Override
	public boolean estaVazia() {
		return PILHA_VAZIA == this.tamanho;
	}

	@Override
	public void liberar() {
		while (!estaVazia()) {
			pop();
		}
	}
	
	public void concatenar(PilhaVetor<T> pilha) {
		concatenar(pilha, true);
	}

	private void concatenar(PilhaVetor<T> pilha, boolean concatenar) {
		PilhaVetor<T> backupPilha = new PilhaVetor<>(pilha.tamanho);
		try {
			while (!pilha.estaVazia()) {
				T dado = pilha.pop();
				backupPilha.push(dado);
				this.push(dado);
			}
		} catch (PilhaCheiaException e) {
			pilha.concatenar(backupPilha, false);
			throw e;
		}
		
		if (concatenar) {
			pilha.concatenar(backupPilha, false);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		T dado = null;
		int idx = this.tamanho - UM;
		while (idx >= ZERO) {
			dado = this.info[idx--];
			if (sb.length() > ZERO) {
				sb.append(VIRGULA);
			}
			sb.append(dado.toString());
		}
		return sb.toString();
	}
	
}
