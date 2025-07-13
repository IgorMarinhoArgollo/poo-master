package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.utils.LeitorUtils;
import com.poomaster.app.utils.BuscaUtils;

import java.util.List;
import java.util.Scanner;

public class ExibirStatusCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;

    public ExibirStatusCommand(Scanner scanner, List<Guerreiro> guerreiros, List<Mago> magos) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
        this.magos = magos;
    }

    @Override
    public void executar() {
        Personagem personagemStatus = null;
        while (personagemStatus == null) {
            String nomePersonagem = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do personagem: ");
            if (nomePersonagem == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            personagemStatus = BuscaUtils.buscarPersonagemPorNome(nomePersonagem, guerreiros, magos);
            if (personagemStatus == null) {
                System.out.println("Personagem não encontrado. Tente novamente.\n");
            }
        }
        System.out.println(personagemStatus);
    }
}