package com.poomaster.app.itens;

public enum Slots {
    MAO_DIREITA("mao_direita", "Mão Direita"),
    MAO_ESQUERDA("mao_esquerda", "Mão Esquerda"),
    ARMADURA("armadura", "Armadura");

    private final String valor;
    private final String nomeFormatado;

    Slots(String valor, String nomeFormatado) {
        this.valor = valor;
        this.nomeFormatado = nomeFormatado;
    }

    public String getValor() {
        return valor;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    // Método para verificar se uma string é um slot válido
    public static boolean isValido(String valor) {
        for (Slots slot : values()) {
            if (slot.getValor().equals(valor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nomeFormatado;
    }
}