package com.poomaster.app.itens;

public abstract class Item {
    protected String nome;
    protected int valor;

    public Item(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public abstract boolean isEmpilhavel();

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
