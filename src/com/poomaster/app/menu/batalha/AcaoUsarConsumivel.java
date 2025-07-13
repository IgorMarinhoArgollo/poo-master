package com.poomaster.app.menu.batalha;

import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.utils.LeitorUtils;

import java.util.Scanner;

public class AcaoUsarConsumivel {
    private final Scanner scanner;
    private final Personagem personagem;

    public AcaoUsarConsumivel(Scanner scanner, Personagem personagem) {
        this.scanner = scanner;
        this.personagem = personagem;
    }

    public void executar() {
        String nomePocao = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do consumível: ");
        if (nomePocao == null) {
            System.out.println("Voltando para as opções de ação...");
            return;
        }
        boolean usou = personagem.usarPocao(nomePocao);
        if (!usou) {
            System.out.println("Consumível não encontrado ou não pode ser usado. \n");
        }
    }
}