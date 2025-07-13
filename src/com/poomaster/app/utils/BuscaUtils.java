package com.poomaster.app.utils;

import com.poomaster.app.criaturas.Guerreiro;
import com.poomaster.app.criaturas.Mago;
import com.poomaster.app.criaturas.Personagem;
import com.poomaster.app.criaturas.Criaturas;

import java.util.List;

public class BuscaUtils {
    public static Personagem buscarPersonagemPorNome(String nome, List<Guerreiro> guerreiros, List<Mago> magos) {
        for (Guerreiro g : guerreiros) if (g.getNome().equalsIgnoreCase(nome)) return g;
        for (Mago m : magos) if (m.getNome().equalsIgnoreCase(nome)) return m;
        return null;
    }

    public static Criaturas buscarInimigoPorNome(String nome, List<Criaturas> inimigos) {
        for (Criaturas c : inimigos) if (c.getNome().equalsIgnoreCase(nome)) return c;
        return null;
    }
}