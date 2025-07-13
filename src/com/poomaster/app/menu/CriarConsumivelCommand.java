package com.poomaster.app.menu;

import com.poomaster.app.itens.Consumivel;
import com.poomaster.app.itens.Item;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class CriarConsumivelCommand implements Comando {
    private final Scanner scanner;
    private final List<Item> itens;

    public CriarConsumivelCommand(Scanner scanner, List<Item> itens) {
        this.scanner = scanner;
        this.itens = itens;
    }

    @Override
    public void executar() {
        String nomeConsumivel = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do consumível: ");
        if (nomeConsumivel == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer cura = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Cura: ");
        if (cura == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer valorConsumivel = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Valor: ");
        if (valorConsumivel == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer quantidadeConsumivel = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Quantidade: ");
        if (quantidadeConsumivel == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Consumivel novoConsumivel = new Consumivel(nomeConsumivel, cura, valorConsumivel, quantidadeConsumivel);
        itens.add(novoConsumivel);
        System.out.println("Consumível criado: " + novoConsumivel + "\n");
    }
}