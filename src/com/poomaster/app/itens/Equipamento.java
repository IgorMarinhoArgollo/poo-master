package com.poomaster.app.itens;

// Classe que representa um equipamento (arma, armadura, escudo, etc).
// Equipamentos podem ser equipados em slots específicos e fornecem ataque/defesa.
public class Equipamento extends Item {
    private final int ataque;
    private final int defesa;
    private boolean equipado;
    private final String slot;

    public Equipamento(String nome, int ataque, int defesa, int valor, String slot) {
        super(nome, valor);
        this.ataque = ataque;
        this.defesa = defesa;
        this.equipado = false;

        // Valida se o slot informado é permitido (mão direita, esquerda ou armadura)
        if (!Slots.isValido(slot)) {
            throw new IllegalArgumentException("Slot inválido: " + slot +
                    ". Slots válidos: " + java.util.Arrays.toString(Slots.values()));
        }
        this.slot = slot;
        System.out.println("Equipamento criado: " + this.toString() + " \n");
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public String getSlot() {
        return slot;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    @Override
    public boolean isEmpilhavel() {
        return false;
    }

    @Override
    public String toString() {
        return String.format(
            "%s [ATQ: %d, DEF: %d, Slot: %s, Valor: %d, %s]",
            getNome(),
            getAtaque(),
            getDefesa(),
            getSlot(),
            getValor(),
            isEquipado() ? "Equipado" : "No inventário"
        );
    }
}
