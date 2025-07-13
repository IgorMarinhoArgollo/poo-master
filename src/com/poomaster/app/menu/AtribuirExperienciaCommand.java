package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.utils.LeitorUtils;
import com.poomaster.app.utils.BuscaUtils;

import java.util.List;
import java.util.Scanner;

public class AtribuirExperienciaCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;

    public AtribuirExperienciaCommand(Scanner scanner, List<Guerreiro> guerreiros, List<Mago> magos) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
        this.magos = magos;
    }

    @Override
    public void executar() {
        Personagem personagemExp = null;
        while (personagemExp == null) {
            String nomePersonagem = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do personagem: ");
            if (nomePersonagem == null) {
                System.out.println("operação cancelada \n");
                return;
            }
            personagemExp = BuscaUtils.buscarPersonagemPorNome(nomePersonagem, guerreiros, magos);
            if (personagemExp == null) {
                System.out.println("Personagem não encontrado. Tente novamente.\n");
            }
        }
        Integer quantidadeExp = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Quantidade de experiência a ganhar: ");
        if (quantidadeExp == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        personagemExp.ganharExperiencia(quantidadeExp);
    }
}