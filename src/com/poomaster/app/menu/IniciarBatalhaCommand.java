package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.utils.LeitorUtils;
import com.poomaster.app.utils.BuscaUtils;
import com.poomaster.app.menu.batalha.AcaoAtacar;
import com.poomaster.app.menu.batalha.AcaoUsarHabilidade;
import com.poomaster.app.menu.batalha.AcaoUsarConsumivel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Comando responsável por iniciar e gerenciar o fluxo de batalha entre aliados e inimigos.
// Organiza os turnos, processa ações dos personagens e inimigos até o fim do combate.
public class IniciarBatalhaCommand implements Comando {
    private final Scanner scanner;
    private final List<Guerreiro> guerreiros;
    private final List<Mago> magos;
    private final List<Criaturas> inimigos;

    public IniciarBatalhaCommand(Scanner scanner, List<Guerreiro> guerreiros, List<Mago> magos, List<Criaturas> inimigos) {
        this.scanner = scanner;
        this.guerreiros = guerreiros;
        this.magos = magos;
        this.inimigos = inimigos;
    }

    @Override
    public void executar() {
        List<Personagem> aliadosBatalha = new ArrayList<>();
        List<Criaturas> inimigosBatalha = new ArrayList<>();

        Integer qtdAliados = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Quantos aliados participarão da batalha? ");
        if (qtdAliados == null) { System.out.println("operação cancelada \n"); return; }
        for (int i = 0; i < qtdAliados; i++) {
            Personagem aliado = null;
            while (aliado == null) {
                String nomeAliado = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do aliado #" + (i + 1) + ": ");
                if (nomeAliado == null) { System.out.println("operação cancelada \n"); return; }
                aliado = BuscaUtils.buscarPersonagemPorNome(nomeAliado, guerreiros, magos);
                if (aliado == null) {
                    System.out.println("Aliado não encontrado. Tente novamente. \n");
                }
            }
            aliadosBatalha.add(aliado);
        }

        Integer qtdInimigos = LeitorUtils.lerInteiroPositivoOuVoltar(scanner, "Quantos inimigos participarão da batalha? ");
        if (qtdInimigos == null) { System.out.println("operação cancelada \n"); return; }
        for (int i = 0; i < qtdInimigos; i++) {
            Criaturas inimigo = null;
            while (inimigo == null) {
                String nomeInimigo = LeitorUtils.lerEntradaOuVoltar(scanner, "Nome do inimigo #" + (i + 1) + ": ");
                if (nomeInimigo == null) { System.out.println("operação cancelada \n"); return; }
                inimigo = BuscaUtils.buscarInimigoPorNome(nomeInimigo, inimigos);
                if (inimigo == null) {
                    System.out.println("Inimigo não encontrado. Tente novamente.");
                }
            }
            inimigosBatalha.add(inimigo);
        }

        System.out.println("\nBatalha iniciada!");
        System.out.println("Aliados: ");
        for (Personagem p : aliadosBatalha) System.out.println("- " + p.getNome());
        System.out.println("Inimigos: ");
        for (Criaturas c : inimigosBatalha) System.out.println("- " + c.getNome());
        System.out.println();

        while (!aliadosBatalha.isEmpty() && !inimigosBatalha.isEmpty()) {
            List<Criaturas> ordemTurnos = new ArrayList<>();
            ordemTurnos.addAll(aliadosBatalha);
            ordemTurnos.addAll(inimigosBatalha);
            ordemTurnos.sort((a, b) -> Integer.compare(b.getAgilidade(), a.getAgilidade()));

            System.out.println("\nOrdem de ataque deste turno:");
            for (Criaturas c : ordemTurnos) {
                String tipo = (aliadosBatalha.contains(c)) ? "[Aliado]" : "[Inimigo]";
                System.out.println(tipo + " " + c.getNome() + " - Vida: " + c.getVidaAtual());
            }
            System.out.println();

            for (Criaturas combatente : new ArrayList<>(ordemTurnos)) {
                if (!combatente.isAlive()) continue;

                if (aliadosBatalha.contains(combatente)) {
                    Personagem personagem = (Personagem) combatente;
                    System.out.println("\nTurno de " + personagem.getNome() + "!");
                    boolean acaoValida = false;
                    while (!acaoValida) {
                        // Exibe opções de ação para o personagem no turno
                        System.out.println("Escolha uma ação: \n[1] Atacar  \n[2] Usar habilidade  \n[3] Usar consumível");
                        String acao = LeitorUtils.lerEntradaOuVoltar(scanner, "Digite a opção desejada: ");
                        if (acao == null) {
                            System.out.println("Voltando para as opções de ação...");
                            continue;
                        }
                        switch (acao) {
                            case "1":
                                new AcaoAtacar(scanner, personagem, inimigosBatalha, inimigos).executar();
                                acaoValida = true;
                                break;
                            case "2":
                                new AcaoUsarHabilidade(personagem).executar();
                                acaoValida = true;
                                break;
                            case "3":
                                new AcaoUsarConsumivel(scanner, personagem).executar();
                                acaoValida = true;
                                break;
                            default:
                                System.out.println("Ação inválida. Perdeu o turno! \n");
                                acaoValida = true;
                        }
                    }
                } else if (inimigosBatalha.contains(combatente)) {
                    if (!aliadosBatalha.isEmpty()) {
                        Random random = new Random();
                        int idx = random.nextInt(aliadosBatalha.size());
                        Personagem alvo = aliadosBatalha.get(idx);
                        combatente.ataque(alvo);
                        if (!alvo.isAlive()) {
                            System.out.println(alvo.getNome() + " foi derrotado! \n");
                            aliadosBatalha.remove(alvo);
                        }
                    }
                }
                if (aliadosBatalha.isEmpty() || inimigosBatalha.isEmpty()) break;
            }
            System.out.println("Pressione ENTER para o próximo turno... \n");
            scanner.nextLine();
        }

        if (aliadosBatalha.isEmpty()) {
            System.out.println("Todos os aliados foram derrotados! \n");
        } else {
            System.out.println("Todos os inimigos foram derrotados! \n");
        }

        // Remover personagens mortos das listas principais
        guerreiros.removeIf(g -> !g.isAlive());
        magos.removeIf(m -> !m.isAlive());
    }
}