package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.utils.LeitorUtils;
import java.util.List;
import java.util.Scanner;

public class CriarInimigoCommand implements Comando {
    private final Scanner scanner;
    private final List<Criaturas> inimigos;

    public CriarInimigoCommand(Scanner scanner, List<Criaturas> inimigos) {
        this.scanner = scanner;
        this.inimigos = inimigos;
    }

    @Override
    public void executar() {
        String nomeCriatura = LeitorUtils.lerEntradaOuVoltar(scanner, "Insira o nome do inimigo: ");
        if (nomeCriatura == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Integer forca = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Força: ");
        if (forca == null) { System.out.println("operação cancelada \n"); return; }
        Integer destreza = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Destreza: ");
        if (destreza == null) { System.out.println("operação cancelada \n"); return; }
        Integer constituicao = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Constituição: ");
        if (constituicao == null) { System.out.println("operação cancelada \n"); return; }
        Integer inteligencia = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Inteligência: ");
        if (inteligencia == null) { System.out.println("operação cancelada \n"); return; }
        Integer percepcao = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Percepção: ");
        if (percepcao == null) { System.out.println("operação cancelada \n"); return; }
        Integer agilidade = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Agilidade: ");
        if (agilidade == null) { System.out.println("operação cancelada \n"); return; }

        Criaturas novaCriatura = new Criaturas(nomeCriatura, forca, destreza, constituicao, inteligencia, percepcao, agilidade) {};
        inimigos.add(novaCriatura);
        System.out.println(nomeCriatura + " criado!");
        System.out.println(novaCriatura + " \n");
    }
}