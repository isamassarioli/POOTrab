/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Anoes;

import Principal.Guerreiro;
import Principal.Montador;

/**
 *
 * @author isama
 */
public class AnaoGlutao extends Anao implements Montador {
    
    private PorcoDeGuerra montaria;
    
    public AnaoGlutao(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso);
        if(temMontaria) {
            this.montaria = new PorcoDeGuerra(nome, idade, peso);
        }
    }

    @Override
    public int atacar() {
        return 30;
    }

    @Override
    public Guerreiro getMontaria() {
        return montaria;
    }

    // Método para liberar o Porco de Guerra quando o Glutão morre
    public PorcoDeGuerra liberarMontaria() {
        PorcoDeGuerra porcoLiberado = this.montaria;
        this.montaria = null;
        return porcoLiberado;
    }

    @Override
    public int getTipo() {
        return 4;
    }

    @Override
    public String toString() {
        String montariaStr = montaria != null ? " (tem montaria)" : " (não tem montaria)";
        return getTipoNome() + ": " + getNome() + ", " + getIdade() + " anos, " + (int)getPeso() + " kilos" + montariaStr;
    }
}
