package com.poomaster.app.utils;

import java.util.Random;

// Classe utilitária para rolagem de dados de diferentes faces.
// Centraliza a aleatoriedade do sistema, permitindo rolar dados de 3, 4, 6, 8, 10, 12, 20 e 100 faces.
// Usada em testes de ataque, defesa, habilidades e efeitos aleatórios do jogo.
public abstract class Dado {

    private static final Random random = new Random();

    public static int roll(int faces) {
        if (faces <= 0) {
            throw new IllegalArgumentException("O dado deve ter pelo menos 1 face.");
        }
        return random.nextInt(faces) + 1;
    }

    public static int roll20() {
        return roll(20);
    }

    public static int roll100() {
        return roll(100);
    }

    public static int roll6() {
        return roll(6);
    }

    public static int roll10() {
        return roll(10);
    }

    public static int roll12() {
        return roll(12);
    }

    public static int roll8() {
        return roll(8);
    }

    public static int roll4() {
        return roll(4);
    }

    public static int roll3() {
        return roll(3);
    }

}