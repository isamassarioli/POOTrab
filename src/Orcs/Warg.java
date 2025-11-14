/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Orcs;

/**
 *
 * @author isama
 */
public class Warg extends Orc{

    protected OrcAcougueiro montador;

    public Warg(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    public void setMontador(OrcAcougueiro montador) {
        this.montador = montador;
    }

    //Se ele é montado pelo Açougueiro, ele não ataca. Senão, ataca normalmente.
    @Override
    public int atacar() {
        if (montador != null){
            return 0; // Warg montado por Açougueiro não ataca
        } else {
            return 15; // Ataque base do Warg
        }
    }

    public int atacarComFila(java.util.List<Orc> fila) {
        // Encontra a posição deste Warg na fila usando equals
        int idx = -1;
        for (int i = 0; i < Integer.MAX_VALUE && i < fila.size(); i++) {
            if (this.equals(fila.get(i))) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            // Não está na fila, ataque padrão
            return atacar();
        }
        int bonus = 0;
        // Conta quantos Wargs estão atrás dele em sequência
        for (int i = idx + 1; i < Integer.MAX_VALUE && i < fila.size(); i++) {
            Orc o = fila.get(i);
            if (o instanceof Warg) {
                bonus += 5;
            } else {
                break;
            }
        }
        return 15 + bonus;
    }

    @Override
    public int getTipo() {
        return 5;
    }

    public Warg(OrcSoldado soldado) {
    super(soldado.getNome(), soldado.getIdade(), (int) soldado.getPeso());
    }

    @Override
    public boolean estaMorto() { //se o Açougueiro morre o Warg que ele monta morre junto com ele
        return this.energia <= 0 || (montador != null && montador.estaMorto());
    }
}
