package com.poomaster.app;

public class Item {
    private String nome;
    private int ataque;
    private int defesa;
    private boolean equipado;
    private int valor;
    private int cura;
    private int quantidade;
    private final boolean empilhavel;
    private final String slot;


    public Item(String nome, int ataque, int defesa, int valor, int cura, boolean empilhavel, String slot) {
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.equipado = false; // Por padrão, não equipado
        this.valor = valor;
        this.cura = cura;
        this.quantidade = 1;
        this.empilhavel = empilhavel;
        this.slot = slot;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getSlot() {
        return slot;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCura() {
        return cura;
    }

    public void setCura(int cura) {
        this.cura = cura;
    }

    public int getQuantidade() { return quantidade; }
    public boolean isEmpilhavel() { return empilhavel; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void aumentarQuantidade(int q) { this.quantidade += q; }
    public void diminuirQuantidade(int q) { this.quantidade -= q; }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", ataque=" + ataque +
                ", defesa=" + defesa +
                ", equipado=" + equipado +
                ", valor=" + valor +
                ", cura=" + cura +
                '}';
    }
}
