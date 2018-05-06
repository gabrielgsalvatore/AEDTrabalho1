package br.furb.aed.trabalho.listaEstatica;

public class ListaEstatica<T extends Object> {

    public static final int RESULTADO_NAO_ENCONTRADO = -1;

    private static final int TAMANHO_INICIAL = 0;
    private static final int IDX_PRIMEIRO_ELEMENTO = 0;
    private static final int TAMANHO_INICIAL_ARRAY = 10;

    private static final int INTEGER_UM = 1;
    private static final int DIVISOR_METADE = 2;

    private static final String VIRGULA = ",";
    private static final String VAZIO = "";

    private T[] info;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public ListaEstatica() {
        this.info = (T[]) new Object[TAMANHO_INICIAL_ARRAY];
        this.tamanho = TAMANHO_INICIAL;
    }

    @SuppressWarnings("unchecked")
    private void redimencionar() {
        int tamanhoAtual = this.tamanho;
        int novoTamanho = tamanhoAtual + TAMANHO_INICIAL_ARRAY;
        T[] novo = (T[]) new Object[novoTamanho];

        for (int idxLista = IDX_PRIMEIRO_ELEMENTO; idxLista < tamanhoAtual; idxLista++) {
            novo[idxLista] = this.info[idxLista];
        }

        this.info = novo;
    }

    public void inserir(T valor) {
        if (this.info.length == this.tamanho) {
            redimencionar();
        }
        this.info[this.tamanho] = valor;
        tamanho++;
    }

    public void exibir() {
        for (int idxLista = IDX_PRIMEIRO_ELEMENTO; idxLista < this.tamanho; idxLista++) {
            System.out.println(this.info[idxLista]);
        }
    }

    public int buscar(T valor) {
        if (null != valor) {
            for (int idxLista = IDX_PRIMEIRO_ELEMENTO; idxLista < this.tamanho; idxLista++) {
                if (valor.equals(this.info[idxLista])) {
                    return idxLista;
                }
            }
        }
        return RESULTADO_NAO_ENCONTRADO;
    }

    public void retirar(T valor) {
        if (null != valor) {
            boolean valorEncontrado = false;
            for (int idxLista = IDX_PRIMEIRO_ELEMENTO; idxLista < this.tamanho; idxLista++) {
                T elemento = this.info[idxLista];
                if (valorEncontrado) {
                    this.info[idxLista - INTEGER_UM] = elemento;
                } else if (valor.equals(elemento)) {
                    valorEncontrado = true;
                }
            }
            this.tamanho--;
        }
    }

    @SuppressWarnings("unchecked")
    public void liberar() {
        this.info = (T[]) new Object[TAMANHO_INICIAL_ARRAY];
        this.tamanho = TAMANHO_INICIAL;
    }

    public T obterElemento(int posicao) {
        if (IDX_PRIMEIRO_ELEMENTO > posicao || this.tamanho <= posicao) {
            throw new IndexOutOfBoundsException();
        }
        return this.info[posicao];
    }

    public boolean estaVazia() {
        return TAMANHO_INICIAL == this.tamanho;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        String resultado = VAZIO;
        for (int idxLista = IDX_PRIMEIRO_ELEMENTO; idxLista < this.tamanho; idxLista++) {
            if (idxLista > IDX_PRIMEIRO_ELEMENTO) {
                resultado += VIRGULA;
            }
            resultado += this.info[idxLista];
        }
        return resultado;
    }

    public void inverter() {
        T backup;
        int meio = this.tamanho / DIVISOR_METADE;
        for (int esquerda = IDX_PRIMEIRO_ELEMENTO, direita = this.tamanho - INTEGER_UM; esquerda < meio; esquerda++, direita--) {
            backup = this.info[esquerda];
            this.info[esquerda] = this.info[direita];
            this.info[direita] = backup;
        }
    }

}
