package com.poomaster.app.itens;

public class Equipamento extends Item {
    private int ataque;
    private int defesa;
    private boolean equipado;
    private String slot;

    public Equipamento(String nome, int ataque, int defesa, int valor, String slot) {
        super(nome, valor);
        this.ataque = ataque;
        this.defesa = defesa;
        this.equipado = false;

        if (!Slots.isValido(slot)) {
            throw new IllegalArgumentException("Slot inválido: " + slot +
                    ". Slots válidos: " + java.util.Arrays.toString(Slots.values()));
        }
        this.slot = slot;
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

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    @Override
    public boolean isEmpilhavel() {
        return false;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "nome='" + nome + '\'' +
                ", ataque=" + ataque +
                ", defesa=" + defesa +
                ", slot='" + slot + '\'' +
                ", valor=" + valor +
                ", equipado=" + equipado +
                '}';
    }
}
