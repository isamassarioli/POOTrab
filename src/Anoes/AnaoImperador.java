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
public class AnaoImperador extends Anao implements Montador {
    
    private static boolean existeImperador = false;
    private PorcoDeGuerra montaria;

    public AnaoImperador(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso);
        this.energia = 250; // Imperador é criado com 250 pontos de energia
        if (existeImperador) {
            throw new IllegalStateException("Já existe um Imperador! Apenas uma unidade é permitida.");
        }
        if (temMontaria) {
            this.montaria = new PorcoDeGuerra(nome, idade, peso);
        }
        existeImperador = true;
    }

    @Override
    public Guerreiro getMontaria() {
        return montaria;
    }
    
    // Método para liberar o Porco de Guerra quando o Imperador morre
    public PorcoDeGuerra liberarMontaria() {
        PorcoDeGuerra porcoLiberado = this.montaria;
        this.montaria = null;
        return porcoLiberado;
    }

    @Override
    public int atacar() {
        return 30;
    }

    // Método para resetar o controle de existência do Imperador (útil para testes)
    public static void resetExistencia() {
        existeImperador = false;
    }

    @Override
    public int getTipo() {
        return 6;
    }

    
    @Override
    public int getEnergia() {
        return this.energia;
    }

    @Override
    public String toString() {
        String montariaStr = montaria != null ? " (tem montaria)" : " (não tem montaria)";
        return getTipoNome() + ": " + getNome() + ", " + getIdade() + " anos, " + (int)getPeso() + " kilos" + montariaStr;
    }
}
