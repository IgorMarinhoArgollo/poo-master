package com.poomaster.app.menu;

import com.poomaster.app.itens.Equipamento;
import com.poomaster.app.itens.Item;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class CriarEquipamentoCommand implements Comando {
    private final Scanner scanner;
    private final List<Item> itens;

    public CriarEquipamentoCommand(Scanner scanner, List<Item> itens) {
        this.scanner = scanner;
        this.itens = itens;
    }

    @Override
    public void executar() {
        String nomeEquip = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do equipamento: ");
        if (nomeEquip == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer ataque = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Ataque: ");
        if (ataque == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer defesa = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Defesa: ");
        if (defesa == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer valorEquip = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Valor: ");
        if (valorEquip == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        String slot = LeitorUtils.lerSlotValidoOuVoltar(scanner, "Slot (mao_direita, mao_esquerda, armadura): ");
        if (slot == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        try {
            Equipamento novoEquipamento = new Equipamento(nomeEquip, ataque, defesa, valorEquip, slot);
            itens.add(novoEquipamento);
            System.out.println("Equipamento criado: " + novoEquipamento + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar equipamento: " + e.getMessage() + "\n");
        }
    }
}