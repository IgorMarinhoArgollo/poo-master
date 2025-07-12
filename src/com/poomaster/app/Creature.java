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
        this.vidaAtual = vidaMaxima;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
        this.agilidade = agilidade;
    }

    /////////////////////////////////////GETTERS
    public String getNome() {
        return nome;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getForca() {
        return forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getPercepcao() {
        return percepcao;
    }

    ///////////////////////////////////////SETTERS
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = Math.min(Math.max(vidaAtual, 0), getVidaMaxima());
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public void setPercepcao(int percepcao) {
        this.percepcao = percepcao;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    ////////////////////// MÉTODOS PARA RECEBER DANO
    public void recebeDano(int amount) {
        int danoFinal = calcularDanoRecebido(amount);

        setVidaAtual(getVidaAtual() - danoFinal);

        System.out.println(nome + " sofreu " + danoFinal + " de dano. Vida atual: " + getVidaAtual() + "/" + getVidaMaxima());

        if (!isAlive()) {
            System.out.println(nome + " morreu!");
        }
    }

    // Para aplicar os modificadores
    protected int calcularDanoRecebido(int danoBase) {
        return danoBase;
    }

    // Verifica se a criatura está morta
    public boolean isAlive() {
        return vidaAtual > 0;
    }

    ///////////////////////////////// MÉTODO DE ATAQUE
    public void ataque(Creature alvo) {}
 }
