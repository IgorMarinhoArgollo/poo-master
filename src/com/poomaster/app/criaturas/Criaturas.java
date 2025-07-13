package com.poomaster.app.criaturas;
import com.poomaster.app.utils.Dado;

import static com.poomaster.app.utils.Constantes.*;


public abstract class Criaturas {
    protected final String nome;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int forca;
    protected int destreza;
    protected int constituicao;
    protected int inteligencia;
    protected int percepcao;
    protected final int agilidade;

    public Criaturas(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
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
    public int recebeDano(int amount) {
        int danoFinal = calcularDanoRecebido(amount);

        setVidaAtual(getVidaAtual() - danoFinal);

        System.out.println(nome + " sofreu " + danoFinal + " de dano. Vida atual: " + getVidaAtual() + "/" + getVidaMaxima());

        if (!isAlive()) {
            System.out.println(nome + " morreu!");
        }
        return danoFinal;
    }

    public int recebeDano(int amount, boolean magico) {
        int danoFinal = calcularDanoRecebido(amount, magico);
        setVidaAtual(getVidaAtual() - danoFinal);
        System.out.println(nome + " sofreu " + danoFinal + " de dano. Vida atual: " + getVidaAtual() + "/" + getVidaMaxima());
        if (!isAlive()) {
            System.out.println(nome + " morreu!");
        }
        return danoFinal;
    }

    // Para aplicar os modificadores
    protected int calcularDanoRecebido(int danoBase) {
        System.out.println(getNome() + " vai calcular o dano sofrido:");
        System.out.println("- Dano base recebido: " + danoBase);

        // Defesa padrão: Constituição
        int defesa = getConstituicao();
        System.out.println("- Defesa (Constituição): " + defesa);

        int dano = danoBase - defesa;
        System.out.println("- Dano após defesa: " + dano);

        dano = Math.max(dano, DANO_MINIMO);
        System.out.println("- Dano final aplicado (mínimo 1): " + dano + "\n");

        return dano;
    }

    protected int calcularDanoRecebido(int danoBase, boolean magico) {
        System.out.println(getNome() + " vai calcular o dano sofrido:");
        System.out.println("- Dano base recebido: " + danoBase);

        int defesa = magico ? getInteligencia() : getConstituicao();
        System.out.println("- Defesa (" + (magico ? "Inteligência" : "Constituição") + "): " + defesa);

        int dano = danoBase - defesa;
        System.out.println("- Dano após defesa: " + dano);

        dano = Math.max(dano, DANO_MINIMO);
        System.out.println("- Dano final aplicado (mínimo 1): " + dano + "\n");

        return dano;
    }

    // Verifica se a criatura está morta
    public boolean isAlive() {
        return vidaAtual > 0;
    }

    ////////////////////////////// MÉTODO DE ATAQUE
    public void ataque(Criaturas alvo) {
        if (alvo == null) {
            System.out.println(getNome() + " não pode atacar um alvo nulo!");
            return;
        }

        System.out.println(getNome() + " tenta atacar " + alvo.getNome() + "!");

        boolean ataqueAcertou = tentarAcertar(alvo);

        if (!ataqueAcertou) {
            System.out.println(getNome() + " errou o ataque! \n");
            consumirBuffSeAtivo();
            return;
        }

        boolean critico = verificarCritico();
        if (critico) {
            System.out.println("🎯 CRÍTICO!");
        }

        int danoTotal = calcularDanoAtaque(alvo, critico);

        int danoSofrido = alvo.recebeDano(danoTotal);
        System.out.println(getNome() + " causou " + danoSofrido + " de dano em " + alvo.getNome() + " \n");

        if (!alvo.isAlive()) {
            System.out.println("💀 " + alvo.getNome() + " foi derrotado por " + getNome() + "!");
        }
        consumirBuffSeAtivo();
    }

    protected int calcularDanoAtaque(Criaturas alvo, boolean critico) {
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
    protected boolean tentarAcertar(Criaturas alvo) {
        int rolagemAtaque = Dado.roll20();
        int totalAtaque = rolagemAtaque + getDestreza();

        int rolagemDefesa = Dado.roll20();
        int totalDefesa = rolagemDefesa + alvo.getAgilidade();

        System.out.println(getNome() + " rolou " + rolagemAtaque + " + " + getDestreza() + " (Destreza) = " + totalAtaque + " para atacar.");
        System.out.println(alvo.getNome() + " rolou " + rolagemDefesa + " + " + alvo.getAgilidade() + " (Agilidade) = " + totalDefesa + " para defender. \n");

        return totalAtaque >= totalDefesa;
    }

    protected boolean verificarCritico() {
        return Dado.roll20() == VALOR_CRITICO;
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

    @Override
    public String toString() {
        return String.format(
            "%s [Vida: %d/%d, Força: %d, Destreza: %d, Constituição: %d, Inteligência: %d, Percepção: %d, Agilidade: %d]",
            getNome(),
            getVidaAtual(),
            getVidaMaxima(),
            getForca(),
            getDestreza(),
            getConstituicao(),
            getInteligencia(),
            getPercepcao(),
            getAgilidade()
        );
    }
}
