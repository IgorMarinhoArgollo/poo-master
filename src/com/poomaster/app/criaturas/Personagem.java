package com.poomaster.app.criaturas;

import com.poomaster.app.itens.*;
import com.poomaster.app.utils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.poomaster.app.utils.Constantes.*;

public abstract class Personagem extends Criaturas {
    protected final ArrayList<Item> inventario;
    protected int capacidade;
    protected final Map<String, Equipamento> equipamentos;
    protected int nivel;
    protected int experiencia;
    protected int buff;

    public Personagem(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
        super(nome, forca, destreza, constituicao, inteligencia, percepcao, agilidade);
        this.capacidade = calcularCapacidade(forca);
        this.inventario = new ArrayList<>();
        this.equipamentos = new HashMap<>();
        this.nivel = 1;
        this.experiencia = 0;
        this.buff = 0;
        inicializarSlots();
    }

    //////////////////////////////////// EXPERIÊNCIA E NÍVEL
    public void ganharExperiencia(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade de experiência não pode ser negativa");
        }

        setExperiencia(getExperiencia() + quantidade);
        System.out.println(getNome() + " ganhou " + quantidade + " de experiência. Total: " + getExperiencia());

        verificarSubidaNivel();
    }

    private void subirNivel() {
        setNivel(getNivel() + 1);
        aplicarBonusAtributos();
        atualizarVidaMaxima();
        atualizarCapacidade();
        System.out.println(getNome() + " subiu para o nível " + getNivel() + "!");
    }

    // Método sobrescrito pelas subclasses (Guerreiro, Mago, etc)
    protected abstract void aplicarBonusAtributos();

    //////////////////////////////////// BUFF
    protected void consumirBuffSeAtivo() {
        if (buff > 0) {
            buff--;
            if (buff == 0) {
                System.out.println(getNome() + " não está mais com efeito ativo.");
            } else {
                System.out.println(getNome() + " ainda tem efeito ativo por " + buff + " turno(s).");
            }
        }
    }

    //////////////////////////////////INVENTÁRIO
    /// // Itens equipados não são contabilizados no inventário
    public void adicionarItem(Item item) {
        if (item.isEmpilhavel()) {
            for (Item i : inventario) {
                if (i.getNome().equalsIgnoreCase(item.getNome()) && i.isEmpilhavel()) {
                    // Criamos a interface para simplificar esse método, permitindo usar o empilhavel no lugar das instâncias específicas
                    Empilhavel empilhavelExistente = (Empilhavel) i;
                    Empilhavel empilhavelNovo = (Empilhavel) item;

                    empilhavelExistente.aumentarQuantidade(empilhavelNovo.getQuantidade());
                    System.out.println("Agora tem " + empilhavelExistente.getQuantidade() +
                            " " + i.getNome() + " no inventário.");
                    return;
                }
            }
        }

        if (inventario.size() < getCapacidade()) {
            inventario.add(item);
            System.out.println(item.getNome() + " foi adicionado ao inventário de " + getNome() +". \n");
        } else {
            System.out.println("Inventário cheio! Não é possível adicionar " + item.getNome() + ".");
        }
    }

    public void removerItem(String nomeItem, int quantidade) {
        if (nomeItem == null || nomeItem.trim().isEmpty()) {
            System.out.println("Nome do item não pode ser nulo ou vazio.");
            return;
        }
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            if (!item.getNome().equalsIgnoreCase(nomeItem)) continue;

            if (item.isEmpilhavel()) {
                // Criamos a interface para simplificar esse método, permitindo usar o empilhavel no lugar das instâncias específicas
                Empilhavel empilhavel = (Empilhavel) item;
                int atual = empilhavel.getQuantidade();

                if (atual > quantidade) {
                    empilhavel.diminuirQuantidade(quantidade);
                    System.out.println("Usou " + quantidade + " de " + item.getNome() +
                            ". Restam " + empilhavel.getQuantidade());
                } else {
                    inventario.remove(i);
                    System.out.println(item.getNome() + " foi removido do inventário.");
                }
            } else {
                inventario.remove(i);
                System.out.println(item.getNome() + " foi removido do inventário.");
            }
            return;
        }

        System.out.println("Item '" + nomeItem + "' não encontrado no inventário.");
    }

    public void listarInventario() {
        System.out.println("Inventário de " + this.nome + ":");
        if (inventario.isEmpty()) {
            System.out.println("-");
        } else {
            for (Item item : inventario) {
                System.out.println("- " + item);
            }
        }
        System.out.println(" \n");
    }

    /////////////////////////////// EQUIPAMENTO
    public boolean equiparItem(String nomeItem) {
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            if (item instanceof Equipamento equip && equip.getNome().equalsIgnoreCase(nomeItem)) {
                String slot = equip.getSlot();

                if (equipamentos.get(slot) != null) {
                    desequiparItem(slot);
                }

                equipamentos.put(slot, equip);
                equip.setEquipado(true);
                inventario.remove(i);
                System.out.println(equip.getNome() + " foi equipado em "+ getNome() +". \n");
                return true;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado ou não é um equipamento.");
        return false;
    }

    public void desequiparItem(String slot) {
         Equipamento itemDesequipado = equipamentos.get(slot);
        if (itemDesequipado != null) {
            itemDesequipado.setEquipado(false);
            equipamentos.put(slot, null);
            adicionarItem(itemDesequipado);
            System.out.println(itemDesequipado.getNome() + " foi desequipado.");
        } else {
            System.out.println("Não há item equipado no slot.");
        }
    }

    public void listarEquipamentos() {
        System.out.println("Equipamentos de " + this.nome + ":");
        for (Slots slot : Slots.values()) {
            String slotValor = slot.getValor();
            Equipamento equip = equipamentos.get(slotValor);
            System.out.print("- " + slot.getNomeFormatado() + ": ");
            if (equip != null) {
                System.out.println(equip.toString());
            } else {
                System.out.println("Vazio");
            }
        }
        System.out.println();
    }

    ////////////////////////////////////// POÇÕES
    public boolean usarPocao(String nomePocao) {
        Consumivel pocao = buscarPocaoNoInventario(nomePocao);

        if (pocao == null) {
            System.out.println(getNome() + " não possui " + nomePocao + " no inventário.");
            return false;
        }

        aplicarEfeitoCura(pocao);
        removerItem(pocao.getNome(), 1);

        return true;
    }

    ////////////////////////////////// AUXILIARES
    private void inicializarSlots() {
        for (Slots slot : Slots.values()) {
            equipamentos.put(slot.getValor(), null);
        }
    }

    private void verificarSubidaNivel() {
        int novoNivel = (getExperiencia() / Constantes.EXP_POR_NIVEL) + 1;

        while (getNivel() < novoNivel) {
            subirNivel();
        }
    }

    private Consumivel buscarPocaoNoInventario(String nomePocao) {
        for (Item item : inventario) {
            if (item instanceof Consumivel consumivel &&
                    consumivel.getNome().equalsIgnoreCase(nomePocao) &&
                    consumivel.getCura() > 0) {
                return consumivel;
            }
        }
        return null;
    }

    private void aplicarEfeitoCura(Consumivel pocao) {
        int curaAplicada = Math.min(pocao.getCura(), vidaMaxima - vidaAtual);
        this.vidaAtual += curaAplicada;

        System.out.println(this.nome + " usou " + pocao.getNome() +
                " e curou " + curaAplicada + " de vida! " +
                "Vida atual: " + vidaAtual + "/" + vidaMaxima);
    }

    protected int calcularCapacidade(int forca) {
        return forca + 5;
    }

    protected Equipamento getMaoDireita() {
        return equipamentos.get(Slots.MAO_DIREITA.getValor());
    }

    protected Equipamento getMaoEsquerda() {
        return equipamentos.get(Slots.MAO_ESQUERDA.getValor());
    }

    protected Equipamento getArmadura() {
        return equipamentos.get(Slots.ARMADURA.getValor());
    }

    protected void atualizarVidaMaxima() {
        setVidaMaxima(getConstituicao() * MULTIPLICADOR_VIDA);
        setVidaAtual(getVidaMaxima());
    }

    protected void atualizarCapacidade() {
        setCapacidade(getForca() + BONUS_CAPACIDADE);
    }

    protected int getDefesaTotalEquipamentos() {
        int defesaTotal = 0;

        for (Slots slot : Slots.values()) {
            Equipamento equipamento = equipamentos.get(slot.getValor());
            if (equipamento != null) {
                defesaTotal += equipamento.getDefesa();
            }
        }
        return defesaTotal;
    }

    //////////////////////////////////// SETTERS
    protected void setBuff(int buff) {
        this.buff = buff;
    }

    protected void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    protected void setNivel(int nivel) {
        this.nivel = nivel;
    }

    protected void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    //////////////////////////////////// GETTERS
    protected int getCapacidade() {
        return capacidade;
    }

    protected int getBuff() {
        return buff;
    }

    protected int getNivel() {
        return nivel;
    }

    protected int getExperiencia() {
        return experiencia;
    }
}