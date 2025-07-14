package com.poomaster.app.itens;

// Interface para itens que podem ser agrupados (empilhados) no inventário.
// Exemplo: moedas, poções.
// Define métodos para obter, aumentar e diminuir quantidade.
public interface Empilhavel {
    int getQuantidade();
    void aumentarQuantidade(int q);
    void diminuirQuantidade(int q);
}