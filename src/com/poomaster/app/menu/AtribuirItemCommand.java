package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.itens.Item;
import com.poomaster.app.utils.BuscaUtils;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class AtribuirItemCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;
    private final List<Item> itens;

    public AtribuirItemCommand(Scanner scanner, List<Guerreiro> guerreiros, List<Mago> magos, List<Item> itens) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
        this.magos = magos;
        this.itens = itens;
    }

    @Override
    public void executar() {
        Personagem personagemEq = null;
        while (personagemEq == null) {
            String nomePersonagemEq = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do personagem: ");
            if (nomePersonagemEq == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            personagemEq = BuscaUtils.buscarPersonagemPorNome(nomePersonagemEq, guerreiros, magos);
            if (personagemEq == null) {
                System.out.println("Personagem não encontrado. Tente novamente. \n");
            }
        }
        Item itemAtribuir = null;
        while (itemAtribuir == null) {
            String nomeItem = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do item: ");
            if (nomeItem == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            for (Item item : itens) {
                if (item.getNome().equalsIgnoreCase(nomeItem)) {
                    itemAtribuir = item;
                    break;
                }
            }
            if (itemAtribuir == null) {
                System.out.println("Item não encontrado. Tente novamente. \n");
            }
        }
        personagemEq.adicionarItem(itemAtribuir);
        itens.remove(itemAtribuir);
        System.out.println("Item atribuído ao personagem! \n");
    }
}