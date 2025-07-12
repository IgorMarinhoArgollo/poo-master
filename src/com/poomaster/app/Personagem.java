package com.poomaster.app;

import java.util.ArrayList;

public abstract class Personagem extends Creature {
    protected final ArrayList<Item> inventario;

    protected int capacidade;
    protected Equipamento maoDireita;
    protected Equipamento maoEsquerda;
    protected Equipamento armadura;

    public Personagem(String nome, int forca, int destreza, int constituicao, int inteligencia, int percepcao, int agilidade) {
        super(nome, forca, destreza, constituicao, inteligencia, percepcao, agilidade);
        this.capacidade = forca + 5;
        this.inventario = new ArrayList<>();
    }

    // INVENTÁRIO
    public void adicionarItem(Item item) {
        if (item.isEmpilhavel()) {
            for (Item i : inventario) {
                if (i.getClass() == item.getClass() && i.getNome().equalsIgnoreCase(item.getNome())) {
                    if (item instanceof Moeda m && i instanceof Moeda im) {
                        im.aumentarQuantidade(m.getQuantidade());
                        System.out.println(m.getNome() + " agora tem " + im.getQuantidade() + " no inventário.");
                        return;
                    } else if (item instanceof Consumivel c && i instanceof Consumivel ic) {
                        ic.aumentarQuantidade(c.getQuantidade());
                        System.out.println(c.getNome() + " agora tem " + ic.getQuantidade() + " no inventário.");
                        return;
                    }
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
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                if (item.isEmpilhavel()) {
                    if (item instanceof Consumivel c) {
                        if (c.getQuantidade() > quantidade) {
                            c.diminuirQuantidade(quantidade);
                            System.out.println("Usou " + quantidade + " de " + c.getNome() + ". Restam " + c.getQuantidade());
                        } else {
                            inventario.remove(i);
                            System.out.println(c.getNome() + " foi removido do inventário.");
                        }
                    } else if (item instanceof Moeda m) {
                        if (m.getQuantidade() > quantidade) {
                            m.diminuirQuantidade(quantidade);
                            System.out.println("Usou " + quantidade + " de " + m.getNome() + ". Restam " + m.getQuantidade());
                        } else {
                            inventario.remove(i);
                            System.out.println(m.getNome() + " foi removido do inventário.");
                        }
                    }
                } else {
                    inventario.remove(i);
                    System.out.println(item.getNome() + " foi removido do inventário.");
                }
                return;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado no inventário.");
    }

    public void listarInventario() {
        System.out.println("Inventário de " + this.nome + ":");
        if (inventario.isEmpty()) {
            System.out.println("- Vazio");
        } else {
            for (Item item : inventario) {
                System.out.println("- " + item);
            }
        }
    }

    // EQUIPAMENTO
    public void equiparItem(String nomeItem) {
        for (int i = 0; i < inventario.size(); i++) {
            Item item = inventario.get(i);
            if (item instanceof Equipamento equip && equip.getNome().equalsIgnoreCase(nomeItem)) {
                String slot = equip.getSlot();

                switch (slot) {
                    case "mao_direita":
                        if (maoDireita != null) desequiparItem("mao_direita");
                        maoDireita = equip;
                        break;
                    case "mao_esquerda":
                        if (maoEsquerda != null) desequiparItem("mao_esquerda");
                        maoEsquerda = equip;
                        break;
                    case "armadura":
                        if (armadura != null) desequiparItem("armadura");
                        armadura = equip;
                        break;
                    default:
                        System.out.println("Slot inválido para equipamento.");
                        return;
                }

                equip.setEquipado(true);
                inventario.remove(i);
                System.out.println(equip.getNome() + " foi equipado no slot " + slot + ".");
                return;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado ou não é um equipamento.");
    }

    public void desequiparItem(String slot) {
        Equipamento itemDesequipado = null;

        switch (slot) {
            case "mao_direita":
                itemDesequipado = maoDireita;
                maoDireita = null;
                break;
            case "mao_esquerda":
                itemDesequipado = maoEsquerda;
                maoEsquerda = null;
                break;
            case "armadura":
                itemDesequipado = armadura;
                armadura = null;
                break;
            default:
                System.out.println("Slot inválido.");
                return;
        }

        if (itemDesequipado != null) {
            itemDesequipado.setEquipado(false);
            adicionarItem(itemDesequipado);
            System.out.println(itemDesequipado.getNome() + " foi desequipado do slot " + slot + ".");
        } else {
            System.out.println("Não há item equipado no slot " + slot + ".");
        }
    }

    public void listarEquipamentos() {
        System.out.println("Equipamentos de " + this.nome + ":");
        System.out.println("- Mão Direita: " + (maoDireita != null ? maoDireita.getNome() : "Vazio"));
        System.out.println("- Mão Esquerda: " + (maoEsquerda != null ? maoEsquerda.getNome() : "Vazio"));
        System.out.println("- Armadura: " + (armadura != null ? armadura.getNome() : "Vazio"));
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

    // Getters
    public int getCapacidade() {
        return capacidade;
    }

    public Equipamento getMaoDireita() {
        return maoDireita;
    }

    public Equipamento getMaoEsquerda() {
        return maoEsquerda;
    }

    public Equipamento getArmadura() {
        return armadura;
    }

    // Setters
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

}
