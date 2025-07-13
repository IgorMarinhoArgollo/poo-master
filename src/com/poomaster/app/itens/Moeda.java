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

    public void aumentarQuantidade(int q) {
        if (q < 0) {
            System.out.println("Não é possível adicionar uma quantidade negativa de moedas. \n");
            return;
        }
        this.quantidade += q;
        System.out.println("Novo numero de " + getNome() + ": " + this.quantidade + " \n");
    }

    public void diminuirQuantidade(int q) {
        if (q > this.quantidade) {
            System.out.println("Não é possível remover " + q + " moedas. " +
                    "Quantidade disponível: " + this.quantidade + ". \n");
        } else {
            this.quantidade -= q;
            System.out.println("Novo numero de " + getNome() + ": " + this.quantidade + " \n");
        }
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
