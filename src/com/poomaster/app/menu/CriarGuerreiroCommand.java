package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.utils.LeitorUtils;
import java.util.List;
import java.util.Scanner;

public class CriarGuerreiroCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;

    public CriarGuerreiroCommand(Scanner scanner, List<Guerreiro> guerreiros) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
    }

    @Override
    public void executar() {
        String nomeGuerreiro = LeitorUtils.lerEntradaOuVoltar(scanner, "Insira o nome do Guerreiro: ");
        if (nomeGuerreiro == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Guerreiro novoGuerreiro = new Guerreiro(nomeGuerreiro);
        guerreiros.add(novoGuerreiro);
        System.out.println("Guerreiro " + nomeGuerreiro + " criado!");
        System.out.println(novoGuerreiro + " \n");
    }
}