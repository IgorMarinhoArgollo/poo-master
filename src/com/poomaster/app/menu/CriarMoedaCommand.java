package com.poomaster.app.menu;

import com.poomaster.app.itens.Moeda;
import com.poomaster.app.itens.Item;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class CriarMoedaCommand implements Comando {
    private final Scanner scanner;
    private final List<Item> itens;

    public CriarMoedaCommand(Scanner scanner, List<Item> itens) {
        this.scanner = scanner;
        this.itens = itens;
    }

    @Override
    public void executar() {
        String nomeMoeda = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome da moeda: ");
        if (nomeMoeda == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer quantidadeMoeda = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Quantidade: ");
        if (quantidadeMoeda == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Moeda novaMoeda = new Moeda(nomeMoeda, quantidadeMoeda);
        itens.add(novaMoeda);
        System.out.println("Moeda criada: " + novaMoeda + "\n");
    }
}