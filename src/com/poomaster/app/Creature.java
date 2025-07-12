package com.poomaster.app;
import static com.poomaster.app.Constants.*;


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
        this.vidaMaxima = constituicao * MULTIPLICADOR_VIDA;
        this.vidaAtual = vidaMaxima;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.percepcao = percepcao;
        this.agilidade = agilidade;
    }

    //////////////////// MÉTODOS PARA RECEBER DANO
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

    ////////////////////////////// MÉTODO DE ATAQUE
    public void ataque(Creature alvo) {
        if (alvo == null) {
            System.out.println(getNome() + " não pode atacar um alvo nulo!");
            return;
        }

        System.out.println(getNome() + " tenta atacar " + alvo.getNome() + "!");

        if (!tentarAcertar(alvo)) {
            System.out.println(getNome() + " errou o ataque!");
            consumirBuffSeAtivo();
            return;
        }

        boolean critico = verificarCritico();
        if (critico) {
            System.out.println("🎯 CRÍTICO!");
        }

        int danoTotal = calcularDanoAtaque(alvo, critico);

        alvo.recebeDano(danoTotal);
        System.out.println(getNome() + " causou " + danoTotal + " de dano em " + alvo.getNome());

        if (!alvo.isAlive()) {
            System.out.println("💀 " + alvo.getNome() + " foi derrotado por " + getNome() + "!");
        }
        consumirBuffSeAtivo();
    }

    // Para ataques físicos - pode ser sobrescrito para ataques mágicos
    protected int calcularDanoAtaque(Creature alvo, boolean critico) {
        int danoBase = calcularDanoBase();
        int bonusArma = calcularBonusArma();
        int defesa = alvo.getConstituicao();

        int danoTotal = danoBase + bonusArma - defesa;
        danoTotal = Math.max(danoTotal, DANO_MINIMO);

        if (critico) {
            danoTotal *= MULTIPLICADOR_CRITICO;
        }

        return danoTotal;
    }

    protected int calcularDanoBase() {
        return getForca() * MULTIPLICADOR_FORCA;
    }

    // Para aplicar o bonus de arma (sobrescrita)
    protected int calcularBonusArma() {
        return 0;
    }

    // Para ataques físicos - Guerreiros e monstros (sobrescrito para ataques mágicos)
    protected boolean tentarAcertar(Creature alvo) {
        int rolagemAtaque = Dice.roll20();
        int totalAtaque = rolagemAtaque + getDestreza();

        int rolagemDefesa = Dice.roll20();
        int totalDefesa = rolagemDefesa + alvo.getAgilidade();

        return totalAtaque >= totalDefesa;
    }

    protected boolean verificarCritico() {
        return Dice.roll20() == VALOR_CRITICO;
    }

    ///////////// METODOS RELACIONADOS A HABILIDADES
    protected void consumirBuffSeAtivo() { }

    //////////////////////////////////////// GETTERS
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

    //////////////////SETTERS (usados ao subir de nível)
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
}
