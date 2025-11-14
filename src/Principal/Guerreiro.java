/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author isama
 */
public abstract class Guerreiro {
    private final String nome;
    private final int idade;
    private final int peso;
    protected int energia;
    protected int efeitoPraga = 0;
    private boolean tonto = false;


    public Guerreiro(String nome, int idade, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.energia = 100;
    }

    // Retorna o valor de ataque do guerreiro
    public abstract int atacar();
    
    // Ataca outro guerreiro causando dano
    public void atacar(Guerreiro alvo) {
        int dano = Math.max( this.atacar() - this.getEfeitoPraga() , 0 );
        alvo.receberDano(dano);

    }

    // Verifica se está vivo (energia > 0)
    public boolean estaVivo() {
        return this.energia > 0;
    }
    
    // Verifica se está morto (energia <= 0)
    public boolean estaMorto() {
        return this.energia <= 0;
    }

    public void receberDano(int i) {
        this.energia -= i;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPeso() {
        return peso;
    }

    public int getEnergia() {
        return energia;
    }
    
    //Método que garante que o nível nunca fique abaixo de 0
    public void aplicarEfeitoPraga(int incremento) {
    // incremento sempre será +1
    int nivelAtual = this.efeitoPraga / 5; // converte 5->1, 10->2, 15->3

    nivelAtual += incremento;

    if (nivelAtual > 3) {
        nivelAtual = 3;
    }

    this.efeitoPraga = nivelAtual * 5;
}


    public int getEfeitoPraga() {
        return efeitoPraga;
    }

    public abstract int getTipo();
    
    //método processarAtaque usa isso para decidir se o adversário poderá contra-atacar.
    public boolean isTonto() {
        return tonto;
    }
    
    //Define se o guerreiro está tonto ou não.
    public void setTonto(boolean tonto) {
        this.tonto = tonto;
    }

    
    /**
     * @return o nome formatado do tipo do guerreiro para exibição
     */
    public String getTipoNome() {
        String nomeClasse = this.getClass().getSimpleName();
        
        // Mapeia os nomes das classes para os nomes de exibição
        switch (nomeClasse) {
            case "GoblinComum":
                return "Goblin Comum";
            case "ReiGoblin":
                return "Rei Goblin";
            case "OrcSoldado":
                return "Soldado Orc";
            case "OrcAcougueiro":
                return "Açougueiro";
            case "Warg":
                return "Warg";
            case "OrcSenhorDasFeras":
                return "Orc Senhor das Feras";
            case "ElfoGuardiao":
                return "Guardião";
            case "ElfoArqueiro":
                return "Arqueiro";
            case "ElfoSuperior":
                return "Superior";
            case "AnaoGlutao":
                return "Glutão";
            case "PorcoDeGuerra":
                return "Porco de Guerra";
            case "AnaoImperador":
                return "Imperador";
            case "Cavalo":
                return "Cavalo";
            default:
                return nomeClasse; // Fallback
        }
    }
}
