package com.poomaster.app.utils;

import com.poomaster.app.itens.Slots;

import java.util.Scanner;

public class LeitorUtils {

    public static Integer lerInteiroPositivoOuVoltar(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("voltar")) {
                return null;
            }
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.println("Digite um número inteiro maior ou igual a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro positivo.");
            }
        }
    }

    public static String lerSlotValidoOuVoltar(Scanner scanner, String mensagem) {
        String slot;
        while (true) {
            System.out.print(mensagem);
            slot = scanner.nextLine().trim();
            if (slot.equalsIgnoreCase("voltar")) {
                return null;
            }
            boolean valido = false;
            for (Slots s : Slots.values()) {
                if (s.getValor().equalsIgnoreCase(slot)) {
                    valido = true;
                    break;
                }
            }
            if (valido) {
                break;
            } else {
                System.out.println("Slot inválido. Opções válidas: MAO_DIREITA, MAO_ESQUERDA, ARMADURA.");
            }
        }
        return slot;
    }

    public static String lerEntradaOuVoltar(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("voltar")) {
            return null;
        }
        return entrada;
    }
}