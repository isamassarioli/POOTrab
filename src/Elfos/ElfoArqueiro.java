/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elfos;


/**
 *
 * @author isama
 */
public class ElfoArqueiro extends Elfo{
    

    public ElfoArqueiro(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso, temMontaria);
    }

    @Override
    public int atacar() {
        return 5;
    }

    @Override
    public int getTipo() {
        return 2;
    }
}
