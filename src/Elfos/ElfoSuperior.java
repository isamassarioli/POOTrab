/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elfos;


/**
 *
 * @author isama
 */
public class ElfoSuperior extends Elfo {

    public ElfoSuperior(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso, temMontaria);
        this.energia = 500; // Elfo Superior é criado com 500 pontos de energia
    }

    @Override
    public int atacar() {
        return Integer.MAX_VALUE; // Ataque fatal - mata instantaneamente
    }

    @Override
    public int getTipo() {
        return 3;    
    }
}
