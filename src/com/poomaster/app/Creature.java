package com.poomaster.app;

public abstract class Creature {
    protected String nome;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int forca;
    protected int destreza;
    protected int constituicao;
    protected int inteligencia;
    protected int percepcao;
    protected int agilidade;

    public Creature(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
        this.nome = nome;
        this.vidaMaxima = constituicao * 10;
        this.vidaAtual = constituicao * 10;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
        this.agilidade = agilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = constituicao * 15;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = this.vidaMaxima;
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

    public int getPercepcao() {
        return percepcao;
    }

    public void setPercepcao(int percepcao) {
        this.percepcao = percepcao;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    // métodos genéricos:
    public void recebeDano(int amount) {
        int danoFinal = calcularDanoRecebido(amount);
        vidaAtual -= danoFinal;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
        System.out.println(nome + " sofreu " + danoFinal + " de dano. Vida atual: " + vidaAtual + "/" + vidaMaxima);
    }

    // Para aplicar os modificadores
    protected int calcularDanoRecebido(int danoBase) {
        return danoBase;
    }

    public boolean isAlive() {
        return vidaAtual > 0;
    }

    public void ataque(Creature alvo) {}
 }
