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

        System.out.println("Bem-vindo ao Mestre das Fichas! Comandos disponíveis:");
        System.out.println(" - criar guerreiro");
        System.out.println(" - criar mago");
        System.out.println(" - criar inimigo");
        System.out.println(" - criar moeda");
        System.out.println(" - criar consumivel");
        System.out.println(" - criar equipamento");
        System.out.println(" - atribuir item");
        System.out.println(" - equipar item");
        System.out.println(" - iniciar batalha");
        System.out.println(" - listar equipamentos");
        System.out.println(" - listar inventario");
        System.out.println(" - remover item");
        System.out.println(" - atribuir experiencia");
        System.out.println(" - exibir status");
        System.out.println(" - exibir personagens");
        System.out.println(" - exibir inimigos");
        System.out.println(" - exit (para sair)");
        System.out.println("Digite comandos (digite 'exit' para sair ou 'voltar' para voltar ao menu):");
        while (true) {
            linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("exit")) {
                break;
            }

            switch (linha.toLowerCase()) {
                case "criar guerreiro":
                    String nomeGuerreiro = lerEntradaOuVoltar(scanner, "Insira o nome do Guerreiro: ");
                    if (nomeGuerreiro == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Guerreiro novoGuerreiro = new Guerreiro(nomeGuerreiro);
                    guerreiros.add(novoGuerreiro);
                    System.out.println("Guerreiro " + nomeGuerreiro + " criado!");
                    System.out.println(novoGuerreiro + " \n");
                    break;

                case "criar mago":
                    String nomeMago = lerEntradaOuVoltar(scanner, "Insira o nome do mago: ");
                    if (nomeMago == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Mago novoMago = new Mago(nomeMago);
                    magos.add(novoMago);
                    System.out.println("Mago " + nomeMago + " criado!");
                    System.out.println(novoMago + " \n");
                    break;

                case "criar inimigo":
                    String nomeCriatura = lerEntradaOuVoltar(scanner, "Insira o nome do inimigo: ");
                    if (nomeCriatura == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Integer forca = lerInteiroPositivoOuVoltar(scanner, "Força: ");
                    if (forca == null) { System.out.println("operação cancelada \n"); break; }
                    Integer destreza = lerInteiroPositivoOuVoltar(scanner, "Destreza: ");
                    if (destreza == null) { System.out.println("operação cancelada \n"); break; }
                    Integer constituicao = lerInteiroPositivoOuVoltar(scanner, "Constituição: ");
                    if (constituicao == null) { System.out.println("operação cancelada \n"); break; }
                    Integer inteligencia = lerInteiroPositivoOuVoltar(scanner, "Inteligência: ");
                    if (inteligencia == null) { System.out.println("operação cancelada \n"); break; }
                    Integer percepcao = lerInteiroPositivoOuVoltar(scanner, "Percepção: ");
                    if (percepcao == null) { System.out.println("operação cancelada \n"); break; }
                    Integer agilidade = lerInteiroPositivoOuVoltar(scanner, "Agilidade: ");
                    if (agilidade == null) { System.out.println("operação cancelada \n"); break; }

                    Criaturas novaCriatura = new Criaturas(nomeCriatura, forca, destreza, constituicao, inteligencia, percepcao, agilidade) {};
                    inimigos.add(novaCriatura);
                    System.out.println(nomeCriatura + " criado!");
                    System.out.println(novaCriatura + " \n");
                    break;

                case "criar moeda":
                    String nomeMoeda = lerEntradaOuVoltar(scanner, "Nome da moeda: ");
                    if (nomeMoeda == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Integer quantidadeMoeda = lerInteiroPositivoOuVoltar(scanner, "Quantidade: ");
                    if (quantidadeMoeda == null) { System.out.println("operação cancelada \n"); break; }
                    Moeda novaMoeda = new Moeda(nomeMoeda, quantidadeMoeda);
                    itens.add(novaMoeda);
                    break;

                case "criar consumivel":
                    String nomeConsumivel = lerEntradaOuVoltar(scanner, "Nome do consumível: ");
                    if (nomeConsumivel == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Integer cura = lerInteiroPositivoOuVoltar(scanner, "Cura: ");
                    if (cura == null) { System.out.println("operação cancelada \n"); break; }
                    Integer valorConsumivel = lerInteiroPositivoOuVoltar(scanner, "Valor: ");
                    if (valorConsumivel == null) { System.out.println("operação cancelada \n"); break; }
                    Integer quantidadeConsumivel = lerInteiroPositivoOuVoltar(scanner, "Quantidade: ");
                    if (quantidadeConsumivel == null) { System.out.println("operação cancelada \n"); break; }
                    Consumivel novoConsumivel = new Consumivel(nomeConsumivel, cura, valorConsumivel, quantidadeConsumivel);
                    itens.add(novoConsumivel);
                    break;

                case "criar equipamento":
                    String nomeEquip = lerEntradaOuVoltar(scanner, "Nome do equipamento: ");
                    if (nomeEquip == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Integer ataque = lerInteiroPositivoOuVoltar(scanner, "Ataque: ");
                    if (ataque == null) { System.out.println("operação cancelada \n"); break; }
                    Integer defesa = lerInteiroPositivoOuVoltar(scanner, "Defesa: ");
                    if (defesa == null) { System.out.println("operação cancelada \n"); break; }
                    Integer valorEquip = lerInteiroPositivoOuVoltar(scanner, "Valor: ");
                    if (valorEquip == null) { System.out.println("operação cancelada \n"); break; }
                    String slot = lerSlotValidoOuVoltar(scanner, "Slot (mao_direita, mao_esquerda, armadura): ");
                    if (slot == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
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
                        String nomePersonagemEq = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                        if (nomePersonagemEq == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
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
                    if (personagemEq == null) break;

                    Item itemAtribuir = null;
                    while (itemAtribuir == null) {
                        String nomeItem = lerEntradaOuVoltar(scanner, "Nome do item: ");
                        if (nomeItem == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
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
                    if (itemAtribuir == null) break;
                    personagemEq.adicionarItem(itemAtribuir);
                    itens.remove(itemAtribuir);
                    System.out.println("Item atribuído ao personagem! \n");
                    break;

                case "equipar item":
                    Personagem personagemEquipar = null;
                    while (personagemEquipar == null) {
                        String nomePersonagem = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                        if (nomePersonagem == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
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
                    if (personagemEquipar == null) break;

                    boolean equipado = false;
                    while (!equipado) {
                        String nomeItemEquipar = lerEntradaOuVoltar(scanner, "Nome do item a equipar: ");
                        if (nomeItemEquipar == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
                        equipado = personagemEquipar.equiparItem(nomeItemEquipar);
                        if (!equipado) {
                            System.out.println("Equipamento não encontrado. Tente novamente. \n");
                        }
                    }
                    break;
                
                case "listar equipamentos":
                    Personagem personagemListarEq = null;
                    while (personagemListarEq == null) {
                        String nomePersonagem = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                        if (nomePersonagem == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
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
                    if (personagemListarEq == null) break;
                    personagemListarEq.listarEquipamentos();
                    break;

                case "listar inventario":
                    Personagem personagemListarInv = null;
                    while (personagemListarInv == null) {
                        String nomePersonagem = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                        if (nomePersonagem == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
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
                    if (personagemListarInv == null) break;
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
                    if (personagemRemover == null) break;

                    String nomeItemRemover = lerEntradaOuVoltar(scanner, "Nome do item a remover: ");
                    if (nomeItemRemover == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    Integer quantidadeRemover = lerInteiroPositivoOuVoltar(scanner, "Quantidade a remover: ");
                    if (quantidadeRemover == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    personagemRemover.removerItem(nomeItemRemover, quantidadeRemover);
                    break;
                case "atribuir experiencia":
                    Personagem personagemExp = null;
                    while (personagemExp == null) {
                        String nomePersonagem = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                        if (nomePersonagem == null) {
                            System.out.println("operação cancelada \n");
                            break;
                        }
                        for (Guerreiro g : guerreiros) {
                            if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                                personagemExp = g;
                                break;
                            }
                        }
                        if (personagemExp == null) {
                            for (Mago m : magos) {
                                if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                    personagemExp = m;
                                    break;
                                }
                            }
                        }
                        if (personagemExp == null) {
                            System.out.println("Personagem não encontrado. Tente novamente.\n");
                        }
                    }
                    if (personagemExp == null) break;

                    Integer quantidadeExp = lerInteiroPositivoOuVoltar(scanner, "Quantidade de experiência a ganhar: ");
                    if (quantidadeExp == null) {
                        System.out.println("operação cancelada \n");
                        break;
                    }
                    personagemExp.ganharExperiencia(quantidadeExp);
                    break;
                case "exibir status":
                  Personagem personagemStatus = null;
                  while (personagemStatus == null) {
                      String nomePersonagem = lerEntradaOuVoltar(scanner, "Nome do personagem: ");
                      if (nomePersonagem == null) {
                          System.out.println("operação cancelada \n");
                          break;
                      }
                      for (Guerreiro g : guerreiros) {
                          if (g.getNome().equalsIgnoreCase(nomePersonagem)) {
                              personagemStatus = g;
                              break;
                          }
                      }
                      if (personagemStatus == null) {
                          for (Mago m : magos) {
                              if (m.getNome().equalsIgnoreCase(nomePersonagem)) {
                                  personagemStatus = m;
                                  break;
                              }
                          }
                      }
                      if (personagemStatus == null) {
                          System.out.println("Personagem não encontrado. Tente novamente.\n");
                      }
                    }
                    if (personagemStatus != null) {
                        System.out.println(personagemStatus);
                    }
                    break;
                case "exibir personagens":
                    System.out.println("Guerreiros:");
                    for (Guerreiro g : guerreiros) {
                        System.out.println("- " + g.getNome());
                    }
                    System.out.println("Magos:");
                    for (Mago m : magos) {
                        System.out.println("- " + m.getNome());
                    }
                    break;

                case "exibir inimigos":
                    System.out.println("Inimigos ativos:");
                    for (Criaturas c : inimigos) {
                        System.out.println("- " + c.getNome());
                    }
                    break;
                case "iniciar batalha":
                List<Personagem> aliadosBatalha = new ArrayList<>();
                List<Criaturas> inimigosBatalha = new ArrayList<>();

                Integer qtdAliados = lerInteiroPositivoOuVoltar(scanner, "Quantos aliados participarão da batalha? ");
                if (qtdAliados == null) { System.out.println("operação cancelada \n"); break; }
                for (int i = 0; i < qtdAliados; i++) {
                    Personagem aliado = null;
                    while (aliado == null) {
                        String nomeAliado = lerEntradaOuVoltar(scanner, "Nome do aliado #" + (i + 1) + ": ");
                        if (nomeAliado == null) { System.out.println("operação cancelada \n"); break; }
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
                    if (aliado == null) { System.out.println("operação cancelada \n"); break; }
                    aliadosBatalha.add(aliado);
                }

                Integer qtdInimigos = lerInteiroPositivoOuVoltar(scanner, "Quantos inimigos participarão da batalha? ");
                if (qtdInimigos == null) { System.out.println("operação cancelada \n"); break; }
                for (int i = 0; i < qtdInimigos; i++) {
                    Criaturas inimigo = null;
                    while (inimigo == null) {
                        String nomeInimigo = lerEntradaOuVoltar(scanner, "Nome do inimigo #" + (i + 1) + ": ");
                        if (nomeInimigo == null) { System.out.println("operação cancelada \n"); break; }
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
                    if (inimigo == null) { System.out.println("operação cancelada \n"); break; }
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

                    // Exibe ordem de ataque e vida atual
                    System.out.println("\nOrdem de ataque deste turno:");
                    for (Criaturas c : ordemTurnos) {
                        String tipo = (aliadosBatalha.contains(c)) ? "[Aliado]" : "[Inimigo]";
                        System.out.println(tipo + " " + c.getNome() + " - Vida: " + c.getVidaAtual());
                    }
                    System.out.println();

                    for (Criaturas combatente : new ArrayList<>(ordemTurnos)) {
                        if (!combatente.isAlive()) continue;

                        // Se for aliado (Personagem)
                        if (aliadosBatalha.contains(combatente)) {
                            Personagem personagem = (Personagem) combatente;
                            System.out.println("\nTurno de " + personagem.getNome() + "!");
                            boolean acaoValida = false;
                            while (!acaoValida) {
                                System.out.println("Escolha uma ação: \n[1] Atacar  \n[2] Usar habilidade  \n[3] Usar consumível");
                                String acao = lerEntradaOuVoltar(scanner, "Digite a opção desejada: ");
                                if (acao == null) {
                                    System.out.println("Voltando para as opções de ação...");
                                    continue; // volta para o menu de ações
                                }
                                switch (acao) {
                                    case "1":
                                        Criaturas alvo = null;
                                        while (alvo == null) {
                                            String nomeAlvo = lerEntradaOuVoltar(scanner, "Nome do inimigo a atacar: ");
                                            if (nomeAlvo == null) {
                                                System.out.println("Voltando para as opções de ação...");
                                                break; // volta para o menu de ações
                                            }
                                            for (Criaturas inimigo : inimigosBatalha) {
                                                if (inimigo.getNome().equalsIgnoreCase(nomeAlvo) && inimigo.isAlive()) {
                                                    alvo = inimigo;
                                                    break;
                                                }
                                            }
                                            if (alvo == null) System.out.println("Inimigo não encontrado ou já derrotado. \n");
                                        }
                                        if (alvo == null) break; // usuário digitou voltar
                                        personagem.ataque(alvo);
                                        if (!alvo.isAlive()) {
                                            System.out.println(alvo.getNome() + " foi derrotado! \n");
                                            inimigos.remove(alvo);
                                            inimigosBatalha.remove(alvo);
                                        }
                                        acaoValida = true;
                                        break;
                                    case "2":
                                        if (personagem instanceof Guerreiro) {
                                            ((Guerreiro) personagem).posturaDefensiva();
                                        } else if (personagem instanceof Mago) {
                                            ((Mago) personagem).miragemArcana();
                                        } else {
                                            System.out.println("Este personagem não possui habilidade especial. \n");
                                        }
                                        acaoValida = true;
                                        break;
                                    case "3": // Usar consumível
                                        String nomePocao = lerEntradaOuVoltar(scanner, "Nome do consumível: ");
                                        if (nomePocao == null) {
                                            System.out.println("Voltando para as opções de ação...");
                                            break; // volta para o menu de ações
                                        }
                                        boolean usou = personagem.usarPocao(nomePocao);
                                        if (!usou) {
                                            System.out.println("Consumível não encontrado ou não pode ser usado. \n");
                                        }
                                        acaoValida = true;
                                        break;
                                    default:
                                        System.out.println("Ação inválida. Perdeu o turno! \n");
                                        acaoValida = true;
                                }
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

                // Remover personagens mortos das listas principais
                guerreiros.removeIf(g -> !g.isAlive());
                magos.removeIf(m -> !m.isAlive());
                break;
                
                default:
                System.out.println("Comando não reconhecido. \n");
                break;
            }
        }
    }

    private static Integer lerInteiroPositivoOuVoltar(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("voltar")) {
                return null;
            }
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.println("Digite um número inteiro maior ou igual a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro positivo.");
            }
        }
    }

    private static String lerSlotValidoOuVoltar(Scanner scanner, String mensagem) {
        String slot;
        while (true) {
            System.out.print(mensagem);
            slot = scanner.nextLine().trim();
            if (slot.equalsIgnoreCase("voltar")) {
                return null;
            }
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

    private static String lerEntradaOuVoltar(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        if (entrada.equalsIgnoreCase("voltar")) {
            return null;
        }
        return entrada;
    }
}