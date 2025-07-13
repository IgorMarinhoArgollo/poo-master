package com.poomaster.app.itens;

public class Consumivel extends Item implements Empilhavel {
    private int cura;
    private int quantidade;

    public Consumivel(String nome, int cura, int valor, int quantidade) {
        super(nome, valor);
        this.cura = cura;
        this.quantidade = quantidade;
    }

    public int getCura() {
        return cura;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void aumentarQuantidade(int q) {
        if (q < 0) {
            System.out.println("Não é possível adicionar uma quantidade negativa de consumíveis. \n");
            return;
        }
        this.quantidade += q;
        System.out.println("Novo número de " + getNome() + ": " + this.quantidade + " \n");
    }

    public void diminuirQuantidade(int q) {
        if (q > this.quantidade) {
            System.out.println("Não é possível remover " + q + " unidades. Quantidade disponível: " + this.quantidade + ". \n");
        } else if (q < 0) {
            System.out.println("Não é possível remover uma quantidade negativa de consumíveis. \n");
        } else {
            this.quantidade -= q;
            System.out.println("Novo número de " + getNome() + ": " + this.quantidade + " \n");
        }
    }

    @Override
    public boolean isEmpilhavel() {
        return true;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [Cura: %d, Quantidade: %d, Valor: %d]",
            getNome(),
            getCura(),
            getQuantidade(),
            getValor()
        );
    }
}
