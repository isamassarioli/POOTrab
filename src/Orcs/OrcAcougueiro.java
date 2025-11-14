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
public class OrcAcougueiro extends Orc implements Montador{

    protected Warg montaria;
    private int bonusAtaque = 0; // Bônus acumulado ao devorar inimigos

    public OrcAcougueiro(String nome, int idade, int peso, boolean temMontaria) {
        super(nome, idade, peso);
        this.energia = 200; // Açougueiro é criado com 200 pontos de energia
        if (temMontaria){
            this.montaria = new Warg(nome, idade, peso);
        }
    }

    /**
     * Inicializa a montaria após a construção completa do objeto.
     * Deve ser chamado após a criação do OrcAcougueiro.
     */
    public void inicializarMontaria() {
        if (this.montaria != null) {
            this.montaria.setMontador(this);
        }
    }

    @Override
    public int atacar() {
        return 20 + bonusAtaque; // Ataque base + bônus de devorar
    }

    @Override
    public Guerreiro getMontaria() {
        return montaria;
    }

    @Override
    public int getTipo() {
        return 4;
    }

    @Override
    public int getEnergia() {
        return this.energia;
    }

    @Override
    public void receberDano(int dano) {
        this.energia -= dano;
        
        // Se estiver com menos de 40 pontos de energia e tiver Warg, devora o Warg
        if (this.energia < 40 && montaria != null && !montaria.estaMorto()) {
            devorarWarg();
        }
    }

    public void aumentarAtaque(int ataqueInimigo) {
        this.bonusAtaque += ataqueInimigo; // Aumenta o ataque ao devorar inimigo
    }

    public Warg getWargMontado() {
        return montaria;
    }

    private void devorarWarg() {
        if (montaria != null) {
            montaria.receberDano(montaria.getEnergia()); // Mata o Warg
            this.energia += 100; // Recupera 100 pontos de energia
            //System.out.println("[HABILIDADE] Açougueiro devorou seu Warg e recuperou 100 de energia!");
            montaria = null;
        }
    }
}

