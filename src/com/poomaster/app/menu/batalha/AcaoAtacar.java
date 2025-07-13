package com.poomaster.app.menu.batalha;

import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.utils.LeitorUtils;

import java.util.List;
import java.util.Scanner;

public class AcaoAtacar {
    private final Scanner scanner;
    private final Personagem personagem;
    private final List<Criaturas> inimigosBatalha;
    private final List<Criaturas> inimigos;

    public AcaoAtacar(Scanner scanner, Personagem personagem, List<Criaturas> inimigosBatalha, List<Criaturas> inimigos) {
        this.scanner = scanner;
        this.personagem = personagem;
        this.inimigosBatalha = inimigosBatalha;
        this.inimigos = inimigos;
    }

    public void executar() {
        Criaturas alvo = null;
        while (alvo == null) {
            String nomeAlvo = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do inimigo a atacar: ");
            if (nomeAlvo == null) {
                System.out.println("Voltando para as opções de ação...");
                return;
            }
            for (Criaturas inimigo : inimigosBatalha) {
                if (inimigo.getNome().equalsIgnoreCase(nomeAlvo) && inimigo.isAlive()) {
                    alvo = inimigo;
                    break;
                }
            }
            if (alvo == null) System.out.println("Inimigo não encontrado ou já derrotado. \n");
        }
        personagem.ataque(alvo);
        if (!alvo.isAlive()) {
            System.out.println(alvo.getNome() + " foi derrotado! \n");
            inimigos.remove(alvo);
            inimigosBatalha.remove(alvo);
        }
    }
}