/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elfos;

import Principal.Guerreiro;

/**
 *
 * @author isama
 */
public class Cavalo extends Guerreiro{

    public Cavalo(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public int atacar() {
        return 0; // Cavalos não atacam
    }

    @Override
    public int getTipo() {
        return 0;
    }
}
