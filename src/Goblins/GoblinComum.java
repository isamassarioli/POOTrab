/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goblins;

public class GoblinComum extends Goblin {
    
    public GoblinComum(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.energia = 50; // Goblin Comum é criado com 50 pontos de energia
    }

    @Override
    public int atacar() {
        return 10; // Ataque fixo para Goblin Comum
    }

    @Override
    public int getTipo() {
        return 1;
    }

    public void recuperarEnergia(int energia) {
        this.energia = energia; // Recupera energia (usado na ressurreição)
    }
}

