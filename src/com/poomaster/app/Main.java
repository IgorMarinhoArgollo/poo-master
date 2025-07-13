package com.poomaster.app;

import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.itens.Equipamento;
import com.poomaster.app.itens.Item;
import com.poomaster.app.itens.Moeda;
import com.poomaster.app.itens.Consumivel;
import com.poomaster.app.itens.Slots;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Instanciando personagens
        Guerreiro thorin = new Guerreiro("Thorin");
        Mago merlin = new Mago("Merlin");

        // Instanciando itens para o Thorin (guerreiro)
        Equipamento espada = new Equipamento("Espada Longa", 5, 0, 100, Slots.MAO_DIREITA.getValor());
        Equipamento escudo = new Equipamento("Escudo de Ferro", 0, 4, 80, Slots.MAO_ESQUERDA.getValor());
        Equipamento armaduraGuerreiro = new Equipamento("Armadura de Placas", 0, 6, 200, Slots.ARMADURA.getValor());
        Equipamento machado = new Equipamento("Machado de Guerra", 7, 0, 120, Slots.MAO_DIREITA.getValor());
        Moeda moedasGuerreiro = new Moeda("Moeda de Ouro", 10);
        Consumivel pocaoPequena = new Consumivel("Poção Pequena", 5, 10, 2);

        // Instanciando itens para Merlin (mago)
        Equipamento armaduraMago = new Equipamento("Manto Arcano", 0, 3, 120, Slots.ARMADURA.getValor());
        Equipamento cajado = new Equipamento("Cajado de Carvalho", 6, 0, 150, Slots.MAO_DIREITA.getValor());
        Equipamento varinha = new Equipamento("Varinha de Foco", 4, 0, 90, Slots.MAO_DIREITA.getValor());
        Moeda moedasMago = new Moeda("Moeda de Ouro", 15);
        Consumivel pocaoMedia = new Consumivel("Poção Média", 15, 25, 1);

        // Adicionando itens ao inventário dos personagens
        thorin.adicionarItem(espada); // Thorin (guerreiro)
        thorin.adicionarItem(escudo); // Thorin (guerreiro)
        thorin.adicionarItem(armaduraGuerreiro); // Thorin (guerreiro)
        thorin.adicionarItem(machado); // Thorin (guerreiro)
        thorin.adicionarItem(moedasGuerreiro); // Thorin (guerreiro)
        thorin.adicionarItem(pocaoPequena); // Thorin (guerreiro)

        merlin.adicionarItem(armaduraMago); //Merlin (Mago)
        merlin.adicionarItem(cajado); //Merlin (Mago)
        merlin.adicionarItem(varinha); //Merlin (Mago)
        merlin.adicionarItem(moedasMago); //Merlin (Mago)
        merlin.adicionarItem(pocaoMedia); //Merlin (Mago)

        // Exemplo de listagem dos inventários antes de equipar
        thorin.listarInventario();
        merlin.listarInventario();

        // Aumenta quantidade de moedas
        moedasGuerreiro.aumentarQuantidade(2); // Thorin tinha 10
        moedasMago.diminuirQuantidade(2); // Merlin tinha 15

        // Alterando quantidade de poções - método não é usado diretamente
        pocaoPequena.diminuirQuantidade(1); // Reduz uma poção de Thorin (tinham 2)
        pocaoMedia.aumentarQuantidade(4); // Aumenta uma poção do Merlin (tinha 1)

        // Equipando itens em Thorin (guerreiro)
        thorin.equiparItem("Espada Longa");
        thorin.equiparItem("Escudo de Ferro");
        thorin.equiparItem("Armadura de Placas");

        // Equipando itens em Merlin (mago)
        merlin.equiparItem("Cajado de Carvalho");
        merlin.equiparItem("Manto Arcano");

        // Exemplo de listagem dos inventários depois de equipar
        thorin.listarInventario();
        merlin.listarInventario();

        // Listando equipamentos equipados
        thorin.listarEquipamentos();
        merlin.listarEquipamentos();

        // Criando o inimigo Lobo com atributos intermediários
        Criaturas aranha = new Criaturas("Aranha", 6, 7, 9, 8, 7, 5) {
        };

        Criaturas lobo = new Criaturas("Lobo", 40, 20, 99, 40, 40, 40) {
        };
        
        // Exibindo informações do Lobo
        System.out.println("Inimigos criados:");
        System.out.println(lobo + " \n" );
        System.out.println(aranha + " \n");
        
        // Acionando postura defensiva de Thorin
        thorin.posturaDefensiva();

        // Acionando miragem arcana de Merlin
        merlin.miragemArcana();

        // Aranha ataca Merlin
        aranha.ataque(merlin);

        // Aranha ataca Thorin
        aranha.ataque(thorin);

        // Thorin (guerreiro) ataca o Aranha
        thorin.ataque(aranha);
        
        // Merlin (mago) ataca o Aranha
        merlin.ataque(aranha);

        lobo.ataque(thorin);
        
        // testando o uso de poção e sua remoção ao usar
        thorin.usarPocao("Poção Média");
        thorin.usarPocao("Poção Pequena");
        thorin.listarInventario();

        // troca arma
        thorin.equiparItem("Machado de Guerra");

        Scanner scanner = new Scanner(System.in);
        String linha;

        List<Guerreiro> guerreiros = new ArrayList<>();
        List<Mago> magos = new ArrayList<>();
        List<Criaturas> inimigos = new ArrayList<>();
        List<Item> itens = new ArrayList<>();

        System.out.println("Digite comandos (digite 'exit' para sair):");
        while (true) {
            linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("exit")) {
                break;
            }

            switch (linha.toLowerCase()) {
               case "criar guerreiro":
                    System.out.print("Insira o nome do Guerreiro: ");
                    String nomeGuerreiro = scanner.nextLine();
                    Guerreiro novoGuerreiro = new Guerreiro(nomeGuerreiro);
                    guerreiros.add(novoGuerreiro);
                    System.out.println("Guerreiro " + nomeGuerreiro + " criado!");
                    System.out.println(novoGuerreiro + " \n");
                    break;

                case "criar mago":
                    System.out.print("Insira o nome do mago: ");
                    String nomeMago = scanner.nextLine();
                    Mago novoMago = new Mago(nomeMago);
                    magos.add(novoMago);
                    System.out.println("Mago " + nomeMago + " criado!");
                    System.out.println(novoMago + " \n");
                    break;

                case "criar inimigo":
                    System.out.print("Insira o nome do inimigo: ");
                    String nomeCriatura = scanner.nextLine();

                    int forca = lerInteiroPositivo(scanner, "Força: ");
                    int destreza = lerInteiroPositivo(scanner, "Destreza: ");
                    int constituicao = lerInteiroPositivo(scanner, "Constituição: ");
                    int inteligencia = lerInteiroPositivo(scanner, "Inteligência: ");
                    int percepcao = lerInteiroPositivo(scanner, "Percepção: ");
                    int agilidade = lerInteiroPositivo(scanner, "Agilidade: ");

                    Criaturas novaCriatura = new Criaturas(nomeCriatura, forca, destreza, constituicao, inteligencia, percepcao, agilidade) {};
                    inimigos.add(novaCriatura);
                    System.out.println(nomeCriatura + " criado!");
                    System.out.println(novaCriatura + " \n");
                    break;

                case "criar moeda":
                    System.out.print("Nome da moeda: ");
                    String nomeMoeda = scanner.nextLine();
                    int quantidadeMoeda = lerInteiroPositivo(scanner, "Quantidade: ");
                    Moeda novaMoeda = new Moeda(nomeMoeda, quantidadeMoeda);
                    itens.add(novaMoeda);
                    break;

                case "criar consumivel":
                    System.out.print("Nome do consumível: ");
                    String nomeConsumivel = scanner.nextLine();
                    int cura = lerInteiroPositivo(scanner, "Cura: ");
                    int valorConsumivel = lerInteiroPositivo(scanner, "Valor: ");
                    int quantidadeConsumivel = lerInteiroPositivo(scanner, "Quantidade: ");
                    Consumivel novoConsumivel = new Consumivel(nomeConsumivel, cura, valorConsumivel, quantidadeConsumivel);
                    itens.add(novoConsumivel);
                    break;

                case "criar equipamento":
                    System.out.print("Nome do equipamento: ");
                    String nomeEquip = scanner.nextLine();
                    int ataque = lerInteiroPositivo(scanner, "Ataque: ");
                    int defesa = lerInteiroPositivo(scanner, "Defesa: ");
                    int valorEquip = lerInteiroPositivo(scanner, "Valor: ");
                    String slot = lerSlotValido(scanner, "Slot (mao_direita, mao_esquerda, armadura): ");
                    try {
                        Equipamento novoEquipamento = new Equipamento(nomeEquip, ataque, defesa, valorEquip, slot);
                        itens.add(novoEquipamento);
                        System.out.println("Equipamento criado: " + novoEquipamento + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao criar equipamento: " + e.getMessage() + "\n");
                    }
                    break;
                    
                case "atribuir item":
                    Personagem personagemEq = null;
                    while (personagemEq == null) {
                        System.out.print("Nome do personagem: ");
                        String nomePersonagemEq = scanner.nextLine();
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagemEq)) {
                                personagemEq = g;
                                break;
                            }
                        }
                        if (personagemEq == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagemEq)) {
                                    personagemEq = m;
                                    break;
                                }
                            }
                        }
                        if (personagemEq == null) {
                            System.out.println("Personagem não encontrado. Tente novamente. \n");
                        }
                    }

                    Item itemAtribuir = null;
                    while (itemAtribuir == null) {
                        System.out.print("Nome do item: ");
                        String nomeItem = scanner.nextLine();
                        for (Item item : itens) {
                            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                                itemAtribuir = item;
                                break;
                            }
                        }
                        if (itemAtribuir == null) {
                            System.out.println("Item não encontrado. Tente novamente. \n");
                        }
                    }
                    personagemEq.adicionarItem(itemAtribuir);
                    itens.remove(itemAtribuir);
                    System.out.println("Item atribuído ao personagem! \n");
                    break;
                case "equipar item":
                    Personagem personagemEquipar = null;
                    while (personagemEquipar == null) {
                        System.out.print("Nome do personagem: ");
                        String nomePersonagem = scanner.nextLine();
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                                personagemEquipar = g;
                                break;
                            }
                        }
                        if (personagemEquipar == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                    personagemEquipar = m;
                                    break;
                                }
                            }
                        }
                        if (personagemEquipar == null) {
                            System.out.println("Personagem não encontrado. Tente novamente.\n");
                        }
                    }

                    boolean equipado = false;
                    while (!equipado) {
                        System.out.print("Nome do item a equipar: ");
                        String nomeItemEquipar = scanner.nextLine();
                        equipado = personagemEquipar.equiparItem(nomeItemEquipar);
                        if (!equipado) {
                            System.out.println("Equipamento não encontrado. Tente novamente. \n");
                        }
                    }
                    break;
                case "iniciar batalha":
                    List<Personagem> aliadosBatalha = new ArrayList<>();
                    List<Criaturas> inimigosBatalha = new ArrayList<>();

                    int qtdAliados = lerInteiroPositivo(scanner, "Quantos aliados participarão da batalha? ");
                    for (int i = 0; i < qtdAliados; i++) {
                        Personagem aliado = null;
                        while (aliado == null) {
                            System.out.print("Nome do aliado #" + (i + 1) + ": ");
                            String nomeAliado = scanner.nextLine();
                            for (Guerreiro g : guerreiros) {
                                if (g.getNome().equalsIgnoreCase(nomeAliado)) {
                                    aliado = g;
                                    break;
                                }
                            }
                            if (aliado == null) {
                                for (Mago m : magos) {
                                    if (m.getNome().equalsIgnoreCase(nomeAliado)) {
                                        aliado = m;
                                        break;
                                    }
                                }
                            }
                            if (aliado == null) {
                                System.out.println("Aliado não encontrado. Tente novamente. \n");
                            }
                        }
                        aliadosBatalha.add(aliado);
                    }

                    int qtdInimigos = lerInteiroPositivo(scanner, "Quantos inimigos participarão da batalha? ");
                    for (int i = 0; i < qtdInimigos; i++) {
                        Criaturas inimigo = null;
                        while (inimigo == null) {
                            System.out.print("Nome do inimigo #" + (i + 1) + ": ");
                            String nomeInimigo = scanner.nextLine();
                            for (Criaturas c : inimigos) {
                                if (c.getNome().equalsIgnoreCase(nomeInimigo)) {
                                    inimigo = c;
                                    break;
                                }
                            }
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
                        // Atualiza ordem dos turnos a cada rodada (caso alguém morra/agilidade mude)
                        List<Criaturas> ordemTurnos = new ArrayList<>();
                        ordemTurnos.addAll(aliadosBatalha);
                        ordemTurnos.addAll(inimigosBatalha);
                        ordemTurnos.sort((a, b) -> Integer.compare(b.getAgilidade(), a.getAgilidade()));

                        for (Criaturas combatente : new ArrayList<>(ordemTurnos)) {
                            if (!combatente.isAlive()) continue;

                            // Se for aliado (Personagem)
                            if (aliadosBatalha.contains(combatente)) {
                                Personagem personagem = (Personagem) combatente;
                                System.out.println("\nTurno de " + personagem.getNome() + "!");
                                System.out.println("Escolha uma ação: \n[1] Atacar  \n[2] Usar habilidade  \n[3] Usar consumível");
                                String acao = scanner.nextLine();

                                switch (acao) {
                                    case "1":
                                        Criaturas alvo = null;
                                        while (alvo == null) {
                                            System.out.print("Nome do inimigo a atacar: ");
                                            String nomeAlvo = scanner.nextLine();
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
                                        break;
                                    case "2":
                                        if (personagem instanceof Guerreiro) {
                                            ((Guerreiro) personagem).posturaDefensiva();
                                        } else if (personagem instanceof Mago) {
                                            ((Mago) personagem).miragemArcana();
                                        } else {
                                            System.out.println("Este personagem não possui habilidade especial. \n");
                                        }
                                        break;
                                    case "3": // Usar consumível
                                        System.out.print("Nome do consumível: ");
                                        String nomePocao = scanner.nextLine();
                                        boolean usou = personagem.usarPocao(nomePocao);
                                        if (!usou) {
                                            System.out.println("Consumível não encontrado ou não pode ser usado. \n");
                                        }
                                        break;
                                    default:
                                        System.out.println("Ação inválida. Perdeu o turno! \n");
                                }
                            } else if (inimigosBatalha.contains(combatente)) {
                                // Inimigo ataca automaticamente um aliado vivo aleatório
                                if (!aliadosBatalha.isEmpty()) {
                                    java.util.Random random = new java.util.Random();
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
                    break;
                case "listar equipamentos":
                    Personagem personagemListarEq = null;
                    while (personagemListarEq == null) {
                        System.out.print("Nome do personagem: ");
                        String nomePersonagem = scanner.nextLine();
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                                personagemListarEq = g;
                                break;
                            }
                        }
                        if (personagemListarEq == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                    personagemListarEq = m;
                                    break;
                                }
                            }
                        }
                        if (personagemListarEq == null) {
                            System.out.println("Personagem não encontrado. Tente novamente.\n");
                        }
                    }
                    personagemListarEq.listarEquipamentos();
                    break;

                case "listar inventario":
                    Personagem personagemListarInv = null;
                    while (personagemListarInv == null) {
                        System.out.print("Nome do personagem: ");
                        String nomePersonagem = scanner.nextLine();
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                                personagemListarInv = g;
                                break;
                            }
                        }
                        if (personagemListarInv == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                    personagemListarInv = m;
                                    break;
                                }
                            }
                        }
                        if (personagemListarInv == null) {
                            System.out.println("Personagem não encontrado. Tente novamente.\n");
                        }
                    }
                    personagemListarInv.listarInventario();
                    break;
                case "remover item":
                    Personagem personagemRemover = null;
                    while (personagemRemover == null) {
                        System.out.print("Nome do personagem: ");
                        String nomePersonagem = scanner.nextLine();
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                                personagemRemover = g;
                                break;
                            }
                        }
                        if (personagemRemover == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                    personagemRemover = m;
                                    break;
                                }
                            }
                        }
                        if (personagemRemover == null) {
                            System.out.println("Personagem não encontrado. Tente novamente.\n");
                        }
                    }

                    System.out.print("Nome do item a remover: ");
                    String nomeItemRemover = scanner.nextLine();

                    int quantidadeRemover = lerInteiroPositivo(scanner, "Quantidade a remover: ");
                    personagemRemover.removerItem(nomeItemRemover, quantidadeRemover);
                    break;
                default:
                    System.out.println("Comando não reconhecido. \n");
                    break;
            }
        }
        scanner.close();
        System.out.println("Programa encerrado.");
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor >= 0) {
                    break;
                } else {
                    System.out.println("Digite um número inteiro maior ou igual a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro positivo.");
            }
        }
        return valor;
    }

    private static String lerSlotValido(Scanner scanner, String mensagem) {
        String slot;
        while (true) {
            System.out.print(mensagem);
            slot = scanner.nextLine().trim();
            boolean valido = false;
            for (Slots s : Slots.values()) {
                if (s.getValor().equalsIgnoreCase(slot)) {
                    valido = true;
                    break;
                }
            }
            if (valido) {
                break;
            } else {
                System.out.println("Slot inválido. Opções válidas: MAO_DIREITA, MAO_ESQUERDA, ARMADURA.");
            }
        }
        return slot;
    }
}