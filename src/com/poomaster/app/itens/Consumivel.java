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

    public void setCura(int cura) {
        this.cura = cura;
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
            "%s [Cura: %d, Quantidade: %d, Valor: %d]",
            getNome(),
            getCura(),
            getQuantidade(),
            getValor()
        );
    }
}
