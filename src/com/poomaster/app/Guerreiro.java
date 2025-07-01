package com.poomaster.app;

import java.util.ArrayList;


public class Guerreiro extends Creature {
    private final ArrayList<Item> inventario;
    private int capacidade;
    private int nivel;
    private int experiencia;
    private Item maoDireita;
    private Item maoEsquerda;
    private Item armadura;
    private int buff;

    public Guerreiro(String nome) {
        super( nome,  8, 5, 7, 3, 4, 5 );
        this.capacidade = this.forca + 5;
        this.inventario = new ArrayList<>();
        this.experiencia = 0;
        this.nivel =  1;
        this.buff = 0;
    }

    public void ganharExperiencia(int quantidade) {
        this.experiencia += quantidade;
        System.out.println(this.nome + " ganhou " + quantidade + " de experiência. Experiência total: " + this.experiencia);

        int novoNivel = (this.experiencia / 100) + 1;

        while (this.nivel < novoNivel) {
            subirNivel();
        }
    }

    public void subirNivel() {
        this.nivel++;

        // Incrementos típicos de Guerreiro
        this.forca += 2;
        this.constituicao += 2;
        this.destreza += 1;

        // Atualiza a vida máxima com base na nova constituição
        this.vidaMaxima = this.constituicao * 10;
        this.vidaAtual = this.vidaMaxima; // Recupera vida ao subir de nível

        // Atualiza capacidade do inventário com nova força
        this.capacidade = this.forca + 5;

        System.out.println(this.nome + " subiu para o nível " + this.nivel + "!");
        System.out.println("Novos atributos:");
        System.out.println("Força: " + this.forca);
        System.out.println("Destreza: " + this.destreza);
        System.out.println("Constituição: " + this.constituicao);
        System.out.println("Vida máxima: " + this.vidaMaxima);
    }

    // Ataque físico
    @Override
    public void ataque(Creature alvo) {
        System.out.println(this.nome + " tenta atacar " + alvo.getNome() + "!");

        // --------------- Verificar acerto ----------------
        int rolagemAtaque = Dice.roll20();
        int totalAtaque = rolagemAtaque + this.destreza;

        int rolagemDefesa = Dice.roll20();
        int totalDefesa = rolagemDefesa + alvo.getAgilidade();

        System.out.println(this.nome + " rolou " + rolagemAtaque + " + DEX(" + this.destreza + ") = " + totalAtaque);
        System.out.println(alvo.getNome() + " rolou " + rolagemDefesa + " + AGI(" + alvo.getAgilidade() + ") = " + totalDefesa);

        if (totalAtaque < totalDefesa) {
            System.out.println(this.nome + " errou o ataque em " + alvo.getNome() + "!");
            consumirBuffSeAtivo();
            return;
        }

        boolean critico = rolagemAtaque == 20;
        if (critico) {
            System.out.println("🎯 ACERTO CRÍTICO! O dano será dobrado!");
        } else {
            System.out.println("O ataque de " + this.nome + " acertou!");
        }

        // --------------- Calcular dano ----------------

        // Dano base do guerreiro
        int danoBase = (this.getForca() * 2);

        // Calcular bônus da arma: 1d4 * soma dos ataques dos equipamentos
        int somaAtaqueEquipamentos = 0;
        if (maoDireita != null) somaAtaqueEquipamentos += maoDireita.getAtaque();
        if (maoEsquerda != null) somaAtaqueEquipamentos += maoEsquerda.getAtaque();

        int multiplicadorArma = Dice.roll(4); // 1d4
        int bonusArma = multiplicadorArma * somaAtaqueEquipamentos;

        System.out.println("Bônus da arma: 1d4(" + multiplicadorArma + ") * " + somaAtaqueEquipamentos + " = " + bonusArma);

        // Defesa física do alvo (equivalente a constituição)
        int defesa = alvo.getConstituicao();

        System.out.println(alvo.getNome() + " possui defesa de " + defesa + " (CON * 0.5)");

        // Dano total antes do crítico
        int danoTotal = danoBase + bonusArma - defesa;
        if (danoTotal < 1) danoTotal = 1;

        // Aplicar crítico se rolou 20
        if (critico) {
            danoTotal *= 2;
        }

        System.out.println(this.nome + " causou " + danoTotal + " de dano em " + alvo.getNome() + "!");
        alvo.recebeDano(danoTotal);

        consumirBuffSeAtivo();
    }


    // O guerreiro toma apenas metade do dano por 5 turnos
    public void posturaDefensiva(Creature alvo) {
        setBuff(5);
    }

    // Tentar Fugir
    public boolean tentarFugir(Creature alvo) {
        System.out.println(this.nome + " tenta fugir de " + alvo.getNome() + "...");

        // Pegando atributos
        int agilidadeJogador = this.agilidade;
        int agilidadeInimigo = alvo.getAgilidade();
        int percepcaoInimigo = alvo.getPercepcao();

        // O inimigo usa o maior entre percepção ou agilidade
        int atributoInimigo = Math.max(agilidadeInimigo, percepcaoInimigo);

        // Cálculo da chance de fuga
        double chanceBase = (double) agilidadeJogador / (agilidadeJogador + atributoInimigo);
        double chancePorcentagem = chanceBase * 100;

        // Rolagem do inimigo
        int rolagem = Dice.roll100();

        System.out.printf("Chance de fuga: %.2f%% | O inimigo rolou %d no dado.%n", chancePorcentagem, rolagem);

        if (rolagem > chancePorcentagem) {
            System.out.println(this.nome + " falhou na tentativa de fuga! " + alvo.getNome() + " percebeu sua tentativa.");
            consumirBuffSeAtivo();
            return false;
        } else {
            System.out.println(this.nome + " conseguiu fugir com sucesso!");
            setBuff(0);
            return true;
        }
    }

    // Inventário
    public void adicionarItem(Item item) {
        if (item.isEmpilhavel()) {
            for (Item i : inventario) {
                if (i.getNome().equalsIgnoreCase(item.getNome())) {
                    i.aumentarQuantidade(item.getQuantidade());
                    System.out.println(item.getNome() + " agora tem " + i.getQuantidade() + " no inventário.");
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
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                if (item.isEmpilhavel()) {
                    if (item.getQuantidade() > quantidade) {
                        item.diminuirQuantidade(quantidade);
                        System.out.println("Usou " + quantidade + " de " + item.getNome() + ". Restam " + item.getQuantidade());
                    } else {
                        inventario.remove(item);
                        System.out.println(item.getNome() + " foi removido do inventário.");
                    }
                } else {
                    inventario.remove(item);
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

    // Usar poção
    public void usarPocao() {
        for (Item item : inventario) {
            if (item.getCura() > 0) {
                this.vidaAtual += item.getCura();
                if (this.vidaAtual > this.vidaMaxima) {
                    this.vidaAtual = this.vidaMaxima;
                }
                System.out.println(this.nome + " usou " + item.getNome() + " e curou " + item.getCura() + " de vida!");
                removerItem(item.getNome(), 1);
                return;
            }
        }
        System.out.println(this.nome + " não tem nenhuma poção para usar.");
        consumirBuffSeAtivo();
    }

    //  EQUIPAR ITEM
    public void equiparItem(String nomeItem) {
        for (Item item : inventario) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                String slot = item.getSlot();

                switch (slot) {
                    case "mao_direita":
                        if (maoDireita != null) {
                            desequiparItem("mao_direita");
                        }
                        maoDireita = item;
                        break;
                    case "mao_esquerda":
                        if (maoEsquerda != null) {
                            desequiparItem("mao_esquerda");
                        }
                        maoEsquerda = item;
                        break;
                    case "armadura":
                        if (armadura != null) {
                            desequiparItem("armadura");
                        }
                        armadura = item;
                        break;
                    default:
                        System.out.println("Slot inválido para equipamento.");
                        return;
                }

                item.setEquipado(true);
                inventario.remove(item);
                System.out.println(item.getNome() + " foi equipado no slot " + slot + ".");
                return;
            }
        }
        System.out.println("Item " + nomeItem + " não encontrado no inventário.");
    }

    //  DESEQUIPAR ITEM
    public void desequiparItem(String slot) {
        Item itemDesequipado = null;

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

    // Listar equipamentos
    public void listarEquipamentos() {
        System.out.println("Equipamentos de " + this.nome + ":");
        System.out.println("- Mão Direita: " + (maoDireita != null ? maoDireita.getNome() : "Vazio"));
        System.out.println("- Mão Esquerda: " + (maoEsquerda != null ? maoEsquerda.getNome() : "Vazio"));
        System.out.println("- Armadura: " + (armadura != null ? armadura.getNome() : "Vazio"));
    }

    // Getter para capacidade do inventário
    public int getCapacidade() {
        return capacidade;
    }

    // Getter para nível
    public int getNivel() {
        return nivel;
    }

    // Getter para experiência
    public int getExperiencia() {
        return experiencia;
    }

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = Math.max(buff, 0); // Nunca menor que zero
    }

    public void consumirBuffSeAtivo() {
        if (getBuff() > 0) {
            setBuff(getBuff() - 1);
            if (getBuff() == 0) {
                System.out.println(this.nome + " não está mais em postura defensiva.");
            } else {
                System.out.println(this.nome + " mantém postura defensiva por " + getBuff() + " turno(s).");
            }
        }
    }

    @Override
    protected int calcularDanoRecebido(int danoBase) {
        if (getBuff() > 0) {
            System.out.println(getNome() + " está em postura defensiva! Dano reduzido pela metade.");
            return Math.max(danoBase / 2, 1); // Reduz pela metade, mínimo de 1 de dano
        }
        return danoBase;
    }
}
