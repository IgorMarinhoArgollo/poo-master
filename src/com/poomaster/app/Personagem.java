package com.poomaster.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Personagem extends Creature {
    protected final ArrayList<Item> inventario;
    protected int capacidade;
    private final Map<String, Equipamento> equipamentos;

    public Personagem(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
        super(nome, forca, destreza, constituicao, inteligencia, percepcao, agilidade);
        this.capacidade = calcularCapacidade(forca);
        this.inventario = new ArrayList<>();
        this.equipamentos = new HashMap<>();
        inicializarSlots();
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
            System.out.println(item.getNome() + " foi adicionado ao inventário.");
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
                return;
            } else {
                inventario.remove(i);
                System.out.println(item.getNome() + " foi removido do inventário.");
                return;
            }
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
    }

    /////////////////////////////////EQUIPAMENTO
    public void equiparItem(String nomeItem) {
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
                System.out.println(equip.getNome() + " foi equipado.");
                return;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado ou não é um equipamento.");
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
            System.out.println("- " + slot.getNomeFormatado() + ": " +
                    (equip != null ? equip.getNome() : "Vazio"));
        }
    }

    // POÇÕES E SEUS AUXILIARES
    public boolean usarPocao(String nomePocao) {
        Consumivel pocao = buscarPocaoNoInventario(nomePocao);

        if (pocao == null) {
            System.out.println(this.nome + " não possui " + nomePocao + " no inventário.");
            return false;
        }

        aplicarEfeitoCura(pocao);
        removerItem(pocao.getNome(), 1);

        return true;
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

    //////////////////////////////////// GETTERS
    public int getCapacidade() {
        return capacidade;
    }

    public Equipamento getMaoDireita() {
        return equipamentos.get(Slots.MAO_DIREITA.getValor());
    }

    public Equipamento getMaoEsquerda() {
        return equipamentos.get(Slots.MAO_ESQUERDA.getValor());
    }

    public Equipamento getArmadura() {
        return equipamentos.get(Slots.ARMADURA.getValor());
    }

    ////////////////////////////////// AUXILIARES
    private void inicializarSlots() {
        for (Slots slot : Slots.values()) {
            equipamentos.put(slot.getValor(), null);
        }
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    protected int calcularCapacidade(int forca) {
        return forca + 5;
    }

    private String formatarNomeSlot(String slot) {
        for (Slots s : Slots.values()) {
            if (s.getValor().equals(slot)) {
                return s.getNomeFormatado();
            }
        }
        return "Slot não encontrado";
    }
}
