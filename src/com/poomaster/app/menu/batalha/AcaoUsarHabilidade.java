package com.poomaster.app.menu.batalha;

import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;

public class AcaoUsarHabilidade {
    private final Personagem personagem;

    public AcaoUsarHabilidade(Personagem personagem) {
        this.personagem = personagem;
    }

    public void executar() {
        if (personagem instanceof Guerreiro) {
            ((Guerreiro) personagem).posturaDefensiva();
        } else if (personagem instanceof Mago) {
            ((Mago) personagem).miragemArcana();
        } else {
            System.out.println("Este personagem não possui habilidade especial. \n");
        }
    }
}