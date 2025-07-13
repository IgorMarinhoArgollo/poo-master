package com.poomaster.app.menu;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.itens.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {
    private final Map<String, Comando> comandos = new HashMap<>();
    private final Scanner scanner;

    public MenuPrincipal(
        Scanner scanner,
        List<Guerreiro> guerreiros,
        List<Mago> magos,
        List<Criaturas> inimigos,
        List<Item> itens
    ) {
        this.scanner = scanner;
        comandos.put("criar guerreiro", new CriarGuerreiroCommand(scanner, guerreiros));
        comandos.put("criar mago", new CriarMagoCommand(scanner, magos));
        comandos.put("criar inimigo", new CriarInimigoCommand(scanner, inimigos));
        comandos.put("criar moeda", new CriarMoedaCommand(scanner, itens));
        comandos.put("criar consumivel", new CriarConsumivelCommand(scanner, itens));
        comandos.put("criar equipamento", new CriarEquipamentoCommand(scanner, itens));
        comandos.put("atribuir item", new AtribuirItemCommand(scanner, guerreiros, magos, itens));
        comandos.put("equipar item", new EquiparItemCommand(scanner, guerreiros, magos));
        comandos.put("listar equipamentos", new ListarEquipamentosCommand(scanner, guerreiros, magos));
        comandos.put("listar inventario", new ListarInventarioCommand(scanner, guerreiros, magos));
        comandos.put("remover item", new RemoverItemCommand(scanner, guerreiros, magos));
        comandos.put("atribuir experiencia", new AtribuirExperienciaCommand(scanner, guerreiros, magos));
        comandos.put("exibir status", new ExibirStatusCommand(scanner, guerreiros, magos));
        comandos.put("exibir personagens", new ExibirPersonagensCommand(guerreiros, magos));
        comandos.put("exibir inimigos", new ExibirInimigosCommand(inimigos));
        comandos.put("iniciar batalha", new IniciarBatalhaCommand(scanner, guerreiros, magos, inimigos));
    }

    public void exibirMenu() {
        System.out.println("Bem-vindo ao Mestre das Fichas! Comandos disponíveis:");
        System.out.println(" - criar guerreiro");
        System.out.println(" - criar mago");
        System.out.println(" - criar inimigo");
        System.out.println(" - criar moeda");
        System.out.println(" - criar consumivel");
        System.out.println(" - criar equipamento");
        System.out.println(" - atribuir item");
        System.out.println(" - equipar item");
        System.out.println(" - listar equipamentos");
        System.out.println(" - listar inventario");
        System.out.println(" - remover item");
        System.out.println(" - atribuir experiencia");
        System.out.println(" - exibir status");
        System.out.println(" - exibir personagens");
        System.out.println(" - exibir inimigos");
        System.out.println(" - iniciar batalha");
        System.out.println(" - exit (para sair)");
        System.out.println("Digite comandos (digite 'exit' para sair ou 'voltar' para voltar ao menu):");
    }

    public void executar() {
        String linha;
        while (true) {
            linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("exit")) break;

            Comando comando = comandos.get(linha.toLowerCase());
            if (comando != null) {
                comando.executar();
            } else {
                System.out.println("Comando não reconhecido. \n");
            }
        }
    }
}