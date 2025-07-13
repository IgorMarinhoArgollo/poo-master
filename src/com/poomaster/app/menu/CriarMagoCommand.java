package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.utils.LeitorUtils;
import java.util.List;
import java.util.Scanner;

public class CriarMagoCommand implements Comando {
    private final Scanner scanner;
    private final List<Mago> magos;

    public CriarMagoCommand(Scanner scanner, List<Mago> magos) {
        this.scanner = scanner;
        this.magos = magos;
    }

    @Override
    public void executar() {
        String nomeMago = LeitorUtils.lerEntradaOuVoltar(scanner, "Insira o nome do mago: ");
        if (nomeMago == null) {
            System.out.println("operação cancelada \n");
            return;
        }
        Mago novoMago = new Mago(nomeMago);
        magos.add(novoMago);
        System.out.println("Mago " + nomeMago + " criado!");
        System.out.println(novoMago + " \n");
    }
}