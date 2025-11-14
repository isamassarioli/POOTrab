/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Orcs;

import Principal.Guerreiro;
import Principal.Montador;

/**
 *
 * @author isama
 */
public class OrcSoldado extends Orc implements Montador{

    protected Warg montaria;
    
    public OrcSoldado(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso);
        if (temMontaria){
            this.montaria = new Warg(nome, idade, peso);
        }
    }

    @Override
    public int atacar() {
        return 20;
    }

    @Override
    public int getTipo() {
        return 3;
    }

    @Override
    public Guerreiro getMontaria() {
        return montaria;
    }

    public boolean wargPodeAtacar() {
        return montaria != null && !montaria.estaMorto();
    }

    public int ataqueDoWarg() {
        if (montaria != null) {
            return montaria.atacar();
        }
        return 0;
    }

    public Warg liberarMontaria() {
        Warg wargLiberado = this.montaria;
        this.montaria = null;
        return wargLiberado;
    }   
}
