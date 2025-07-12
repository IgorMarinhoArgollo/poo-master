package com.poomaster.app;

import static com.poomaster.app.Constantes.*;

public class Mago extends Personagem{
    public Mago(String nome) {
        super(nome, 3, 4, 6, 8, 7, 5);
    }

    /////////////////////////////////// ATAQUE
    @Override
    protected boolean tentarAcertar(Criaturas alvo) {
        int ataqueMagico = Dado.roll20() + getInteligencia();
        int defesaMagica = Dado.roll20() + Math.max(alvo.getInteligencia(), alvo.getPercepcao());

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
        System.out.println(getNome() + " conjurou a Miragem arcana! Agora existem mais 3 ilusões para confundir o inimigo.");
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

        // Se o buff estiver ativo (Miragem Arcana), role o dado para verificar se atinge o mago ou as ilusões
        if (getBuff() > 0) {
            int roll = Dado.roll4();
            // O ataque só atinge o Mago caso a rolagem dos dados seja 1
            if (roll != 1) {
                System.out.println(getNome() + " está com Miragem Arcana ativada! Ataque atingiu uma ilusão.");
                return 0;
            } else {
                System.out.println(getNome() + " foi atingido diretamente apesar do espelhamento!");
            }
        }

        if (mitigacao > 0) {
            System.out.println(getNome() + " mitigou " + (int) mitigacao + " de dano com armadura.");
        }

        return Math.max(dano, DANO_MINIMO);
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
                System.out.println(equip.getNome() + " foi equipado.");
                return;
            }
        }

        System.out.println("Item " + nomeItem + " não encontrado ou não é um equipamento.");
    }

}
