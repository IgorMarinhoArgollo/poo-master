package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;

import java.util.List;

public class ExibirPersonagensCommand implements Comando {
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;

    public ExibirPersonagensCommand(List<Guerreiro> guerreiros, List<Mago> magos) {
        this.guerreiros = guerreiros;
        this.magos = magos;
    }

    @Override
    public void executar() {
        System.out.println("Guerreiros:");
        for (Guerreiro g : guerreiros) {
            System.out.println("- " + g.getNome());
        }
        System.out.println("Magos:");
        for (Mago m : magos) {
            System.out.println("- " + m.getNome());
        }
    }
}