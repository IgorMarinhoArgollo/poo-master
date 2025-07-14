package com.poomaster.app.itens;

// Enumeração dos slots de equipamento disponíveis para personagens.
// Define valores e métodos auxiliares para validação e formatação de slots.
// Exemplo: MAO_DIREITA, MAO_ESQUERDA, ARMADURA.
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

    public static boolean isValido(String valor) {
        for (Slots slot : values()) {
            if (slot.getValor().equals(valor)) {
                return true;
            }
        }
        return false;
    }
}