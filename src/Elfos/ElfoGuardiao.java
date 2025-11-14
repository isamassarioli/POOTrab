/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Elfos;


/**
 *
 * @author isama
 */
public class ElfoGuardiao extends Elfo{

    public ElfoGuardiao(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso, temMontaria);
    }
    
    @Override
    public int atacar() {
        return 25;
    }

    @Override
    public int getTipo() {
        return 1;
    }
}
