package com.poomaster.app.utils;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.itens.*;

import java.util.List;

public class CenarioTeste {
    public static void carregar(
        List<Guerreiro> guerreiros,
        List<Mago> magos,
        List<Criaturas> inimigos,
        List<Item> itens
    ) {
        // Instanciando personagens
        Guerreiro thorin = new Guerreiro("Thorin");
        Mago merlin = new Mago("Merlin");

        // Instanciando itens para o Thorin (guerreiro)
        Equipamento espada = new Equipamento("Espada Longa", 5, 0, 100, Slots.MAO_DIREITA.getValor());
        Equipamento escudo = new Equipamento("Escudo de Ferro", 0, 4, 80, Slots.MAO_ESQUERDA.getValor());
        Equipamento armaduraGuerreiro = new Equipamento("Armadura de Placas", 0, 6, 200, Slots.ARMADURA.getValor());
        Equipamento machado = new Equipamento("Machado de Guerra", 7, 0, 120, Slots.MAO_DIREITA.getValor());
        Moeda moedasGuerreiro = new Moeda("Moeda de Ouro", 10);
        Consumivel pocaoPequena = new Consumivel("Poção Pequena", 5, 10, 2);

        // Instanciando itens para Merlin (mago)
        Equipamento armaduraMago = new Equipamento("Manto Arcano", 0, 3, 120, Slots.ARMADURA.getValor());
        Equipamento cajado = new Equipamento("Cajado de Carvalho", 6, 0, 150, Slots.MAO_DIREITA.getValor());
        Equipamento varinha = new Equipamento("Varinha de Foco", 4, 0, 90, Slots.MAO_DIREITA.getValor());
        Moeda moedasMago = new Moeda("Moeda de Ouro", 15);
        Consumivel pocaoMedia = new Consumivel("Poção Média", 15, 25, 1);

        // Adicionando itens ao inventário dos personagens
        thorin.adicionarItem(espada);
        thorin.adicionarItem(escudo);
        thorin.adicionarItem(armaduraGuerreiro);
        thorin.adicionarItem(machado);
        thorin.adicionarItem(moedasGuerreiro);
        thorin.adicionarItem(pocaoPequena);

        merlin.adicionarItem(armaduraMago);
        merlin.adicionarItem(cajado);
        merlin.adicionarItem(varinha);
        merlin.adicionarItem(moedasMago);
        merlin.adicionarItem(pocaoMedia);

        // Exemplo de listagem dos inventários antes de equipar
        thorin.listarInventario();
        merlin.listarInventario();

        // Aumenta quantidade de moedas
        moedasGuerreiro.aumentarQuantidade(2);
        moedasMago.diminuirQuantidade(2);

        // Alterando quantidade de poções
        pocaoPequena.diminuirQuantidade(1);
        pocaoMedia.aumentarQuantidade(4);

        // Equipando itens em Thorin (guerreiro)
        thorin.equiparItem("Espada Longa");
        thorin.equiparItem("Escudo de Ferro");
        thorin.equiparItem("Armadura de Placas");

        // Equipando itens em Merlin (mago)
        merlin.equiparItem("Cajado de Carvalho");
        merlin.equiparItem("Manto Arcano");

        // Exemplo de listagem dos inventários depois de equipar
        thorin.listarInventario();
        merlin.listarInventario();

        // Listando equipamentos equipados
        thorin.listarEquipamentos();
        merlin.listarEquipamentos();

        // Criando inimigos
        Criaturas aranha = new Criaturas("Aranha", 6, 7, 9, 8, 7, 5) {};
        Criaturas lobo = new Criaturas("Lobo", 40, 20, 99, 40, 40, 40) {};

        System.out.println("Inimigos criados:");
        System.out.println(lobo + " \n");
        System.out.println(aranha + " \n");

        // Acionando habilidades
        thorin.posturaDefensiva();
        merlin.miragemArcana();

        // Ataques de exemplo
        aranha.ataque(merlin);
        aranha.ataque(thorin);
        thorin.ataque(aranha);
        merlin.ataque(aranha);
        lobo.ataque(thorin);

        // Testando o uso de poção e sua remoção ao usar
        thorin.usarPocao("Poção Média");
        thorin.usarPocao("Poção Pequena");
        thorin.listarInventario();

        // Troca arma
        thorin.equiparItem("Machado de Guerra");

        // Adiciona personagens e inimigos às listas principais
        guerreiros.add(thorin);
        magos.add(merlin);
        inimigos.add(aranha);
        inimigos.add(lobo);

        // Adiciona itens criados à lista de itens (opcional)
        itens.add(espada);
        itens.add(escudo);
        itens.add(armaduraGuerreiro);
        itens.add(machado);
        itens.add(moedasGuerreiro);
        itens.add(pocaoPequena);
        itens.add(armaduraMago);
        itens.add(cajado);
        itens.add(varinha);
        itens.add(moedasMago);
        itens.add(pocaoMedia);
    }
}