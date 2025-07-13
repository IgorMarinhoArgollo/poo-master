package com.poomaster.app;

import com.poomaster.app.criaturas.Criaturas;
import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.itens.Item;
import com.poomaster.app.menu.MenuPrincipal;
import com.poomaster.app.utils.CenarioTeste;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Guerreiro> guerreiros = new ArrayList<>();
        List<Mago> magos = new ArrayList<>();
        List<Criaturas> inimigos = new ArrayList<>();
        List<Item> itens = new ArrayList<>();

        // Carrega o cenário de teste
        CenarioTeste.carregar(guerreiros, magos, inimigos, itens);
        
        // Jogo
        MenuPrincipal menu = new MenuPrincipal(scanner, guerreiros, magos, inimigos, itens);
        menu.exibirMenu();
        menu.executar();
    }
}