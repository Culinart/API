package culinart.utils;

import java.util.ArrayList;
import java.util.List;

public class ListaObj<T> {

    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        } else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.print(vetor[i] + "\t");
            }
            System.out.println();
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }
        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return null;
        }
        return vetor[indice];
    }

    public void setElemento(int indice, T elemento) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("Índice inválido!");
        } else {
            vetor[indice] = elemento;
        }
    }

    public void limpa() {
        for (int i = 0; i < nroElem; i++) { // Change ">" to "<"
            nroElem--;
            vetor[i] = null;
        }
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < nroElem; i++) {
            result.add(vetor[i]);
        }
        return result;
    }
}
