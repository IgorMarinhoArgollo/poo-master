package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Criaturas;

import java.util.List;

public class ExibirInimigosCommand implements Comando {
    private final List<Criaturas> inimigos;

    public ExibirInimigosCommand(List<Criaturas> inimigos) {
        this.inimigos = inimigos;
    }

    @Override
    public void executar() {
        System.out.println("Inimigos ativos:");
        for (Criaturas c : inimigos) {
            System.out.println("- " + c.getNome());
        }
    }
}