/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elfos;

import Principal.Guerreiro;
import Principal.Montador;

/**
 *
 * @author isama
 */
public abstract class Elfo extends Guerreiro implements Montador{
    
    protected Cavalo montaria;
    
    public Elfo(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso);
        if (temMontaria){
            this.montaria = new Cavalo (nome, idade, peso);
        }
    }
    @Override
    public Guerreiro getMontaria() {
        return montaria;
    }

    @Override
    public void receberDano(int dano) {
        // Se tiver montaria (Cavalo), o dano vai para o Cavalo primeiro
        if (montaria != null && !montaria.estaMorto()) {
            montaria.receberDano(dano);
            // Se o Cavalo morreu, remove a montaria
            if (montaria.estaMorto()) {
                montaria = null;
            }
        } else {
            // Se não tiver montaria ou o Cavalo morreu, o Elfo recebe o dano
            this.energia -= dano;
        }
    }
    
}
