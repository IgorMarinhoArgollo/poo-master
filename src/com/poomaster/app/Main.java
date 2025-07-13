package com.poomaster.app;

import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.itens.Equipamento;
import com.poomaster.app.itens.Moeda;
import com.poomaster.app.itens.Consumivel;
import com.poomaster.app.itens.Slots;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
        thorin.adicionarItem(espada); // Thorin (guerreiro)
        thorin.adicionarItem(escudo); // Thorin (guerreiro)
        thorin.adicionarItem(armaduraGuerreiro); // Thorin (guerreiro)
        thorin.adicionarItem(machado); // Thorin (guerreiro)
        thorin.adicionarItem(moedasGuerreiro); // Thorin (guerreiro)
        thorin.adicionarItem(pocaoPequena); // Thorin (guerreiro)

        merlin.adicionarItem(armaduraMago); //Merlin (Mago)
        merlin.adicionarItem(cajado); //Merlin (Mago)
        merlin.adicionarItem(varinha); //Merlin (Mago)
        merlin.adicionarItem(moedasMago); //Merlin (Mago)
        merlin.adicionarItem(pocaoMedia); //Merlin (Mago)

        // Exemplo de listagem dos inventários antes de equipar
        thorin.listarInventario();
        merlin.listarInventario();

        // Aumenta quantidade de moedas
        moedasGuerreiro.aumentarQuantidade(2); // Thorin tinha 10
        moedasMago.diminuirQuantidade(2); // Merlin tinha 15

        // Alterando quantidade de poções - método não é usado diretamente
        pocaoPequena.diminuirQuantidade(1); // Reduz uma poção de Thorin (tinham 2)
        pocaoMedia.aumentarQuantidade(4); // Aumenta uma poção do Merlin (tinha 1)

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

        // Criando o inimigo Lobo com atributos intermediários
        Criaturas lobo = new Criaturas("Lobo", 9, 20, 99, 9, 9, 9) {};

        // Exibindo informações do Lobo
        System.out.println("Inimigo criado:");
        System.out.println(lobo + " \n" );

        // Acionando postura defensiva de Thorin
        thorin.posturaDefensiva();

        // Acionando miragem arcana de Merlin
        merlin.miragemArcana();

        // Lobo ataca Merlin
        lobo.ataque(merlin);

        // Lobo ataca Thorin
        lobo.ataque(thorin);

        // Thorin (guerreiro) ataca o lobo
        thorin.ataque(lobo);

        // Merlin (mago) ataca o lobo
        merlin.ataque(lobo);
        
        Scanner scanner = new Scanner(System.in);
        String linha;

        System.out.println("Digite comandos (digite 'exit' para sair):");
        while (true) {
            linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Você digitou: " + linha);
        }
        scanner.close();
        System.out.println("Programa encerrado.");
    }
}