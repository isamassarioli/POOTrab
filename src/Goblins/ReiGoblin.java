/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Goblins;

import java.util.List;

public class ReiGoblin extends Goblin {
    private static boolean existeReiGoblin = false;

    public ReiGoblin(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.energia = 300; // Rei Goblin é criado com 300 pontos de energia
        if (existeReiGoblin) {
            throw new IllegalStateException("Já existe um Rei Goblin! Apenas uma unidade é permitida.");
        }
        existeReiGoblin = true;
    }

    @Override
    public String toString() {
        return getTipoNome() + ": " + getNome() + ", " + getIdade() + " anos, " + (int)getPeso() + " kilos";
    }

    @Override
    public int atacar() {
        return 100;
    }

    @Override
    public int getTipo() {
        return 2;
    }

    public static boolean isExisteReiGoblin() {
        return existeReiGoblin;
    }

    public void convocacaoGoblinsComunsMortos(List<GoblinComum> goblinsMortos) {
        for (GoblinComum goblin : goblinsMortos) {
            goblin.recuperarEnergia(50); // Recupera 50 de energia
        }
        goblinsMortos.clear(); // Limpa a lista após a convocação
    }

}
