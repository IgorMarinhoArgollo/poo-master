package com.poomaster.app;

public class Creature {
    protected String nome;
    protected int nivel;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int forca;
    protected int destreza;
    protected int constituicao;
    protected int inteligencia;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public Creature(String nome, int nivel, int vidaMaxima, int forca, int destreza, int constituicao, int inteligencia) {
        this.nome = nome;
        this.nivel = nivel;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima; // vida começa cheia
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
    }

    // métodos genéricos:
    public void recebeDano(int amount) {
        vidaAtual -= amount;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
        System.out.println(nome + " sofreu " + amount + " de dano. Vida atual: " + vidaAtual + "/" + vidaMaxima);
    }

    public boolean isAlive() {
        return vidaAtual > 0;
    }

    public void recebeCura(int amount) {
        vidaAtual += amount;
        if (vidaAtual > vidaMaxima) {
            vidaAtual = vidaMaxima;
        }
        System.out.println(nome + " foi curado em " + amount + " pontos. Vida atual: " + vidaAtual + "/" + vidaMaxima);
    }

}
