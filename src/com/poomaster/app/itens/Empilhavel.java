package com.poomaster.app.itens;

public interface Empilhavel {
    int getQuantidade();
    void aumentarQuantidade(int q);
    void diminuirQuantidade(int q);
}