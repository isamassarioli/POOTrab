/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Anoes;


/**
 *
 * @author isama
 */
public class PorcoDeGuerra extends Anao {

    public PorcoDeGuerra(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.energia = 250; // Porcos de Guerra são criados com 250 pontos de energia
    }
    
    @Override
    public int atacar() {
        return 0; // Porco de Guerra não causa dano direto    
    }

    @Override
    public int getTipo() {
        return 5;
    }
    
    public int pragaDosPorcos(int qtdAtaquesRecebidos) {
        if (qtdAtaquesRecebidos <= 1) {
            return 5;
        } else if (qtdAtaquesRecebidos == 2) {
            return 10;
        } else {
            return 15;
        }
    }

    @Override
    public String toString() {
        return getTipoNome() + ": " + getNome() + ", " + getIdade() + " anos, " + (int)getPeso() + " kilos";
    }
}
