package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.utils.BuscaUtils;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class EquiparItemCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;

    public EquiparItemCommand(Scanner scanner, List<Guerreiro> guerreiros, List<Mago> magos) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
        this.magos = magos;
    }

    @Override
    public void executar() {
        Personagem personagemEquipar = null;
        while (personagemEquipar == null) {
            String nomePersonagem = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do personagem: ");
            if (nomePersonagem == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            personagemEquipar = BuscaUtils.buscarPersonagemPorNome(nomePersonagem, guerreiros, magos);
            if (personagemEquipar == null) {
                System.out.println("Personagem não encontrado. Tente novamente.\n");
            }
        }
        boolean equipado = false;
        while (!equipado) {
            String nomeItemEquipar = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do item a equipar: ");
            if (nomeItemEquipar == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            equipado = personagemEquipar.equiparItem(nomeItemEquipar);
            if (!equipado) {
                System.out.println("Equipamento não encontrado. Tente novamente. \n");
            }
        }
    }
}