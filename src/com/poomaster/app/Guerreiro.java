package com.poomaster.app;
import static com.poomaster.app.Constants.*;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 8, 5, 7, 3, 4, 5);
    }

    /////////////////////////////////// ATAQUE
    @Override
    protected int calcularBonusArma() {
        int somaAtaqueEquip = 0;

        if (getMaoDireita() != null) {
            somaAtaqueEquip += getMaoDireita().getAtaque();
        }
        if (getMaoEsquerda() != null) {
            somaAtaqueEquip += getMaoEsquerda().getAtaque();
        }

        return Dice.roll(LADOS_DADO_BONUS) * somaAtaqueEquip;
    }

    //////////////////////////////// HABILIDADE
    public void posturaDefensiva() {
        setBuff(DURACAO_POSTURA_DEFENSIVA);
        System.out.println(getNome() + " assumiu postura defensiva! Receberá metade do dano por turno.");
    }

    @Override
    protected void consumirBuffSeAtivo() {
        if (getBuff() > 0) {
            setBuff(getBuff() - 1);
            if (getBuff() == 0) {
                System.out.println(getNome() + " não está mais em postura defensiva.");
            } else {
                System.out.println(getNome() + " mantém postura defensiva por " + getBuff() + " turno(s).");
            }
        }
    }

    /////////////////////// ATRIBUTOS POR NÍVEL
    @Override
    protected void aplicarBonusAtributos() {
        setForca(getForca() + BONUS_FORCA_NIVEL);
        setConstituicao(getConstituicao() + BONUS_CONSTITUICAO_NIVEL);
        setDestreza(getDestreza() + BONUS_DESTREZA_NIVEL);
    }

    /////////////////////////////// AUXILIARES
    @Override
    protected int calcularDanoRecebido(int danoBase) {
        if (getBuff() > 0) {
            System.out.println(getNome() + " está em postura defensiva! Dano reduzido pela metade.");
            return Math.max(danoBase / REDUCAO_DANO_DEFENSIVO, DANO_MINIMO);
        }
        return danoBase;
    }
}
