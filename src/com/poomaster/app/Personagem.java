package com.poomaster.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Personagem extends Creature {
    protected final ArrayList<Item> inventario;
    protected int capacidade;
    private final Map<String, Equipamento> equipamentos;

    private static final String SLOT_MAO_DIREITA = "mao_direita";
    private static final String SLOT_MAO_ESQUERDA = "mao_esquerda";
    private static final String SLOT_ARMADURA = "armadura";

    public Personagem(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
        super(nome, forca, destreza, constituicao, inteligencia, percepcao, agilidade);
        this.capacidade = calcularCapacidade(forca);
        this.inventario = new ArrayList<>();
        this.equipamentos = new HashMap<>();
        inicializarSlots();

        // Inicializa os slots vazios
        this.equipamentos.put("mao_direita", null);
        this.equipamentos.put("mao_esquerda", null);
        this.equipamentos.put("armadura", null);
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

        if (inventario.size() < capacidade) {
            inventario.add(item);
            System.out.println(item.getNome() + " foi adicionado ao inventário.");
        } else {
            System.out.println("Inventário cheio! Não é possível adicionar " + item.getNome() + ".");
        }
    }

    public void removerItem(String nomeItem, int quantidade) {
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
        for (Map.Entry<String, Equipamento> entry : equipamentos.entrySet()) {
            String slot = entry.getKey();
            Equipamento equip = entry.getValue();
            System.out.println("- " + formatarNomeSlot(slot) + ": " + (equip != null ? equip.getNome() : "Vazio"));
        }
    }

    // POÇÕES
    public void usarPocao() {
        for (Item item : inventario) {
            if (item instanceof Consumivel c && c.getCura() > 0) {
                this.vidaAtual += c.getCura();
                if (this.vidaAtual > this.vidaMaxima) {
                    this.vidaAtual = this.vidaMaxima;
                }
                System.out.println(this.nome + " usou " + c.getNome() + " e curou " + c.getCura() + " de vida!");
                removerItem(c.getNome(), 1);
                return;
            }
        }
        System.out.println(this.nome + " não tem nenhuma poção para usar.");
    }

    //////////////////////////////////// GETTERS
    public int getCapacidade() {
        return capacidade;
    }

    public Equipamento getMaoDireita() {
        return equipamentos.get("mao_direita");
    }

    public Equipamento getMaoEsquerda() {
        return equipamentos.get("mao_esquerda");
    }

    public Equipamento getArmadura() {
        return equipamentos.get("armadura");
    }

    ////////////////////////////////// SETTERS
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    ////////////////////////////////AUXILIARES
    protected int calcularCapacidade(int forca) {
        return forca + 5;
    }

    private void inicializarSlots() {
        equipamentos.put(SLOT_MAO_DIREITA, null);
        equipamentos.put(SLOT_MAO_ESQUERDA, null);
        equipamentos.put(SLOT_ARMADURA, null);
    }

    private String formatarNomeSlot(String slot) {
        String nomeFormatado = slot.replace("_", " ");
        return nomeFormatado.substring(0, 1).toUpperCase() + nomeFormatado.substring(1);
    }
}
