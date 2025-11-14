/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Orcs;

/**
 *
 * @author isama
 */
public class OrcSenhorDasFeras extends Orc{

    public OrcSenhorDasFeras(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.energia = 400; // Senhor das Feras é criado com 400 pontos de energia
    }

    @Override
    public int atacar() {
        return 50;
    }

    @Override
    public int getTipo() {
        return 6;
    }

    @Override
    public int getEnergia() {
        return this.energia;
    }

    public Warg criarWarg() {
        return new Warg(this.getNome(), this.getIdade(), (int) this.getPeso());
    }
    
}
