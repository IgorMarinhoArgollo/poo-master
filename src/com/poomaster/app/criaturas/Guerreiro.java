package com.poomaster.app.criaturas;
import com.poomaster.app.utils.Dado;

import static com.poomaster.app.utils.Constantes.*;

public class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 8, 5, 7, 3, 4, 6);
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

        return Dado.roll(LADOS_DADO_BONUS_GUERREIRO) * somaAtaqueEquip;
    }

    //////////////////////////////// HABILIDADE
    public void posturaDefensiva() {
        setBuff(DURACAO_POSTURA_DEFENSIVA);
        System.out.println(getNome() + " assumiu postura defensiva! Receberá metade do dano por turno. \n");
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
        setForca(getForca() + BONUS_FORCA_NIVEL_GUERREIRO);
        setConstituicao(getConstituicao() + BONUS_CONSTITUICAO_NIVEL_GUERREIRO);
        setDestreza(getDestreza() + BONUS_DESTREZA_NIVEL_GUERREIRO);
    }

    /////////////////////////////// AUXILIARES
    @Override
    protected int calcularDanoRecebido(int danoBase) {
        int defesaEquipamentos = getDefesaTotalEquipamentos();

        // Mitiga dano com base em √(armadura + constituição), garantindo crescimento desacelerado.
        double mitigacao = Math.sqrt(defesaEquipamentos * getConstituicao());
        int dano = danoBase - (int) mitigacao;

        System.out.println(getNome() + " vai calcular o dano sofrido:");
        System.out.println("- Dano base: " + danoBase);
        System.out.println("- Mitigação (√(armadura(" + defesaEquipamentos + ") * constituição(" + getConstituicao() + "))): " + (int) mitigacao);

        if (getBuff() > 0) {
          int danoParcial = danoBase - (int)mitigacao;
          danoParcial = Math.max(danoParcial, 1);
          System.out.println("- Total parcial: Dano base (" + danoBase + ") - Mitigação (" + (int)mitigacao + ") = " + danoParcial + " (mínimo de 1) \n");
            System.out.println(getNome() + " está em postura defensiva! Dano reduzido pela metade.");
            dano /= REDUCAO_DANO_DEFENSIVO;
        }

        if (mitigacao > 0) {
            System.out.println(getNome() + " mitigou " + (int) mitigacao + " de dano com armadura.");
        }

        dano = Math.max(dano, DANO_MINIMO);
        System.out.println("- Dano final aplicado (mínimo 1): " + dano + "\n");

        return dano;
    }
}
