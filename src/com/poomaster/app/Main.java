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

        Criaturas esqueleto = new Criaturas("Esqueleto", 6, 7, 9, 8, 7, 5) {
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
                    System.out.println("Moeda criada: " + novaMoeda + "\n");
                    break;

                case "criar consumivel":
                    System.out.print("Nome do consumível: ");
                    String nomeConsumivel = scanner.nextLine();
                    int cura = lerInteiroPositivo(scanner, "Cura: ");
                    int valorConsumivel = lerInteiroPositivo(scanner, "Valor: ");
                    int quantidadeConsumivel = lerInteiroPositivo(scanner, "Quantidade: ");
                    Consumivel novoConsumivel = new Consumivel(nomeConsumivel, cura, valorConsumivel, quantidadeConsumivel);
                    itens.add(novoConsumivel);
                    System.out.println("Consumível criado: " + novoConsumivel + "\n");
                    break;

                case "criar equipamento":
                    System.out.print("Nome do equipamento: ");
                    String nomeEquip = scanner.nextLine();
                    int ataque = lerInteiroPositivo(scanner, "Ataque: ");
                    int defesa = lerInteiroPositivo(scanner, "Defesa: ");
                    int valorEquip = lerInteiroPositivo(scanner, "Valor: ");
                    String slot = lerSlotValido(scanner, "Slot (ex: MAO_DIREITA, MAO_ESQUERDA, ARMADURA): ");
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

                    Equipamento equipamento = null;
                    while (equipamento == null) {
                        System.out.print("Nome do item: ");
                        String nomeEquipamento = scanner.nextLine();
                        for (Item item : itens) {
                            if (item instanceof Equipamento && item.getNome().equalsIgnoreCase(nomeEquipamento)) {
                                equipamento = (Equipamento) item;
                                break;
                            }
                        }
                        if (equipamento == null) {
                            System.out.println("Item não encontrado. Tente novamente. \n");
                        }
                    }

                    personagemEq.adicionarItem(equipamento);
                    // Para remover da lista de itens geral - evita atribuir o mesmo item para vários personagens
                    itens.remove(equipamento);
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