package com.poomaster.app.itens;

public class Consumivel extends Item implements Empilhavel {
    private int cura;
    private int quantidade;

    public Consumivel(String nome, int valor, int cura, int quantidade) {
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
        return "Consumivel{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", cura=" + cura +
                ", quantidade=" + quantidade +
                '}';
    }
}
