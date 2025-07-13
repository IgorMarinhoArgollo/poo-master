package com.poomaster.app.criaturas;

import com.poomaster.app.itens.Equipamento;
import com.poomaster.app.itens.Item;
import com.poomaster.app.itens.Slots;
import com.poomaster.app.utils.Dado;

import static com.poomaster.app.utils.Constantes.*;

public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 3, 4, 6, 8, 7, 5);
    }

    /////////////////////////////////// ATAQUE
    @Override
    protected boolean tentarAcertar(Criaturas alvo) {
        int rolagemAtaque = Dado.roll20();
        int ataqueMagico = rolagemAtaque + getInteligencia();

        int rolagemDefesa = Dado.roll20();
        int defesaMagica = rolagemDefesa + Math.max(alvo.getInteligencia(), alvo.getPercepcao());

        System.out.println(getNome() + " rolou " + rolagemAtaque + " + " + getInteligencia() + " (Inteligência) = " + ataqueMagico + " para atacar magicamente.\n");
        System.out.println(alvo.getNome() + " rolou " + rolagemDefesa + " + " + Math.max(alvo.getInteligencia(), alvo.getPercepcao()) + " (Defesa Mágica) = " + defesaMagica + " para defender magicamente.\n");
        System.out.println("Resultado: " + (ataqueMagico >= defesaMagica ? "Ataque mágico acertou!\n" : "Ataque mágico falhou!\n"));

        return ataqueMagico >= defesaMagica;
    }

    @Override
    protected int calcularDanoBase() {
        return getInteligencia() * MULTIPLICADOR_INTELIGENCIA;
    }

    @Override
    protected int calcularBonusArma() {
        Equipamento maoDireita = getMaoDireita();
        return (maoDireita != null) ? maoDireita.getAtaque() : 0;
    }

    @Override
    protected int calcularDanoAtaque(Criaturas alvo, boolean critico) {
        int danoBase = calcularDanoBase();
        int bonusArma = calcularBonusArma();
        int defesa = alvo.getInteligencia(); // Mago ignora constituição, e considera inteligência como defesa mágica

        int danoTotal = danoBase + bonusArma - defesa;
        danoTotal = Math.max(danoTotal, DANO_MINIMO);

        if (critico) {
            danoTotal *= MULTIPLICADOR_CRITICO;
        }

        return danoTotal;
    }

    //////////////////////////////// HABILIDADE
    public void miragemArcana() {
        setBuff(DURACAO_MIRAGEM_ARCANA);
        System.out.println(getNome() + " conjurou a Miragem arcana! Agora existem mais 3 ilusões para confundir o inimigo. \n");
    }

    @Override
    protected void consumirBuffSeAtivo() {
        if (getBuff() > 0) {
            setBuff(getBuff() - 1);
            if (getBuff() == 0) {
                System.out.println(getNome() + " não está mais com a Miragem Arcana ativada - suas ilusões desapareceram.");
            } else {
                System.out.println(getNome() + " está com a Miragem arcana por " + getBuff() + " turno(s), e suas ilusões seguem ativas.");
            }
        }
    }

    /////////////////////// ATRIBUTOS POR NÍVEL
    @Override
    protected void aplicarBonusAtributos() {
        setInteligencia(getInteligencia() + BONUS_INTELIGENCIA_NIVEL_MAGO);
        setPercepcao(getPercepcao() + BONUS_PERCEPCAO_NIVEL_MAGO);
        setConstituicao(getConstituicao() + BONUS_CONSTITUICAO_NIVEL_MAGO);
    }

    /////////////////////////////// AUXILIARES
    @Override
    protected int calcularDanoRecebido(int danoBase) {
        int defesaEquipamentos = getDefesaTotalEquipamentos();

        // Mitigação com base em √(armadura * inteligência), para crescimento desacelerado
        double mitigacao = Math.sqrt(defesaEquipamentos * getInteligencia());
        int dano = danoBase - (int) mitigacao;

        System.out.println(getNome() + " vai calcular o dano sofrido:");
        System.out.println("- Dano base: " + danoBase);
        System.out.println("- Mitigação (√(armadura(" + defesaEquipamentos + ") * inteligência(" + getInteligencia()
            + "))): " + (int) mitigacao);
        
        // Miragem Arcana
        if (getBuff() > 0) {
          int danoParcial = danoBase - (int)mitigacao;
          danoParcial = Math.max(danoParcial, 1);
          System.out.println("- Total parcial: Dano base (" + danoBase + ") - Mitigação (" + (int)mitigacao + ") = " + (danoParcial) + " (mínimo de 1) \n");
          int roll = Dado.roll4();
            System.out.println(getNome() + " está com Miragem Arcana ativada!");
            System.out.println(getNome() + " rola um dado para tentar trocar de lugar com uma ilusão...");
            System.out.println("Resultado do dado: " + roll + " (precisa tirar um número maior que 1 para trocar de lugar com uma das ilusões).");
            if (roll != 1) {
                System.out.println(getNome() + " troca de lugar com uma ilusão e evita o ataque!\n");
                System.out.println("Ataque atingiu uma ilusão.");
                return 0;
            } else {
                System.out.println(getNome() + " não conseguiu trocar de lugar a tempo e foi atingido diretamente!\n");
            }
        }

        if (mitigacao > 0) {
            System.out.println(getNome() + " mitigou " + (int) mitigacao + " de dano com armadura.");
        }

        dano = Math.max(dano, DANO_MINIMO);
        System.out.println("- Dano final aplicado (mínimo 1): " + dano + "\n");

        return dano;
    }

    @Override
    public void equiparItem(String nomeItem) {
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            if (item instanceof Equipamento equip && equip.getNome().equalsIgnoreCase(nomeItem)) {
                String slot = equip.getSlot();
                // Magos precisam de pelo menos uma mão livre para conjurar suas magias
                // Impede magos de equipar qualquer item na mão esquerda
                if (slot.equals(Slots.MAO_ESQUERDA.getValor())) {
                    System.out.println("Magos não podem equipar itens na mão esquerda.");
                    return;
                }

                if (equipamentos.get(slot) != null) {
                    desequiparItem(slot);
                }

                equipamentos.put(slot, equip);
                equip.setEquipado(true);
                inventario.remove(i);
                System.out.println(equip.getNome() + " foi equipado em "+ getNome() +". \n");
                return;
            }
        }

        System.out.println("Item " + nomeItem + " não encontrado ou não é um equipamento.");
    }

}
