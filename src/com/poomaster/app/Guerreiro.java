package com.poomaster.app;

public class Guerreiro extends Personagem {
    private int nivel;
    private int experiencia;
    private int buff;

    public Guerreiro(String nome) {
        super(nome, 8, 5, 7, 3, 4, 5);
        this.experiencia = 0;
        this.nivel = 1;
        this.buff = 0;
    }

    public void ganharExperiencia(int quantidade) {
        this.experiencia += quantidade;
        System.out.println(this.nome + " ganhou " + quantidade + " de experiência. Total: " + this.experiencia);

        int novoNivel = (this.experiencia / 100) + 1;

        while (this.nivel < novoNivel) {
            subirNivel();
        }
    }

    public void subirNivel() {
        this.nivel++;
        setForca(getForca() + 2);
        setConstituicao(getConstituicao() + 2);
        setDestreza(getDestreza() + 1);

        setVidaMaxima(getConstituicao() * 10);
        setVidaAtual(getVidaMaxima());

        setCapacidade(getForca() + 5);

        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
    }

    // Ataque físico
    @Override
    public void ataque(Creature alvo) {
        System.out.println(this.nome + " tenta atacar " + alvo.getNome() + "!");

        int rolagemAtaque = Dice.roll20();
        int totalAtaque = rolagemAtaque + this.destreza;

        int rolagemDefesa = Dice.roll20();
        int totalDefesa = rolagemDefesa + alvo.getAgilidade();

        if (totalAtaque < totalDefesa) {
            System.out.println(this.nome + " errou o ataque!");
            consumirBuffSeAtivo();
            return;
        }

        boolean critico = rolagemAtaque == 20;
        if (critico) {
            System.out.println("🎯 CRÍTICO!");
        }

        int danoBase = this.getForca() * 2;
        int somaAtaqueEquip = 0;
        if (maoDireita != null) somaAtaqueEquip += maoDireita.getAtaque();
        if (maoEsquerda != null) somaAtaqueEquip += maoEsquerda.getAtaque();

        int bonusArma = Dice.roll(4) * somaAtaqueEquip;
        int defesa = alvo.getConstituicao();

        int danoTotal = danoBase + bonusArma - defesa;
        if (danoTotal < 1) danoTotal = 1;
        if (critico) danoTotal *= 2;

        System.out.println(this.nome + " causou " + danoTotal + " de dano em " + alvo.getNome());
        alvo.recebeDano(danoTotal);

        consumirBuffSeAtivo();
    }

    public void posturaDefensiva() {
        setBuff(5);
    }

    public void setBuff(int buff) {
        this.buff = Math.max(buff, 0);
    }

    public int getBuff() {
        return buff;
    }

    public void consumirBuffSeAtivo() {
        if (buff > 0) {
            buff--;
            if (buff == 0) {
                System.out.println(this.nome + " não está mais em postura defensiva.");
            } else {
                System.out.println(this.nome + " mantém postura defensiva por " + buff + " turno(s).");
            }
        }
    }

    @Override
    protected int calcularDanoRecebido(int danoBase) {
        if (buff > 0) {
            System.out.println(getNome() + " está em postura defensiva! Dano reduzido pela metade.");
            return Math.max(danoBase / 2, 1);
        }
        return danoBase;
    }

    // Getters
    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }
}
