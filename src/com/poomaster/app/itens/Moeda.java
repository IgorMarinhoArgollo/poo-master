package com.poomaster.app.itens;

public class Moeda extends Item implements Empilhavel {
    private int quantidade;

    public Moeda(String nome, int quantidade) {
        super(nome, 1);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void aumentarQuantidade(int q) {
        this.quantidade += q;
    }

    public void diminuirQuantidade(int q) {
        this.quantidade -= q;
    }

    @Override
    public boolean isEmpilhavel() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Quantidade: %d]",
            getNome(),
            getQuantidade()
        );
    }
}
