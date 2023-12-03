package culinart.utils;

import lombok.Getter;
@Getter
public class PilhaObj {
    private int[] pilha;
    private int topo;

        public PilhaObj(int tamanho) {
            pilha = new int[tamanho];
            topo = -1;
        }
        public Boolean isEmpty() {
            return topo == -1;
        }
        public Boolean isFull() {
            return topo == pilha.length - 1;
        }
        public void push(int info) {
            if (isFull()) {
                System.out.println("Pilha cheia!");
                throw new IllegalStateException("Pilha cheia");
            }
            else {
                pilha[++topo] = info;
            }
        }
        public int pop() {
            if (isEmpty()) {
                return -1;
            }

            return pilha[topo--];
        }
        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return pilha[topo];
        }
        public void exibe() {
            if (isEmpty()) {
                System.out.println("Pilha vazia");
            }
            else {
                for (int i = topo; i >= 0; i--) {
                    System.out.println(pilha[i]);
                }
            }
        }


}
