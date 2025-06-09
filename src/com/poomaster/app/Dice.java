package com.poomaster.app;

import java.util.Random;

/**
 * Classe utilitária para rolagem de dados.
 * Não deve ser instanciada.
 */
public abstract class Dice {

    private static final Random random = new Random();

    // Método genérico
    public static int roll(int faces) {
        if (faces <= 0) {
            throw new IllegalArgumentException("O dado deve ter pelo menos 1 face.");
        }
        return random.nextInt(faces) + 1;
    }

    // Método específico para D20
    public static int roll20() {
        return roll(20);
    }

    // Método específico para D100
    public static int roll100() {
        return roll(100);
    }

    // Você pode adicionar outros, se quiser:
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
}