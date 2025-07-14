package com.poomaster.app.itens;

// Classe abstrata base para todos os itens do jogo.
// Define nome, valor e método para distinguir itens empilháveis (ex: moedas, poções).
// Subclasses: Equipamento, Consumível, Moeda, etc.
public abstract class Item {
    protected final String nome;
    protected final int valor;

    public Item(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }

    public abstract boolean isEmpilhavel(); // Indica se o item pode ser empilhado no inventário

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
