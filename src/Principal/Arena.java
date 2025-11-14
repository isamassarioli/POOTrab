package Principal;

import java.util.LinkedList;
import java.util.Random;

/**
 * Classe Arena - Gerencia a batalha entre dois lados de guerreiros Usa
 * LinkedList para armazenar as filas de guerreiros
 */
public class Arena {

    private Guerreiro ultimoGuerreiroMorto;
    private Guerreiro matouUltimo;
    private Guerreiro ultimoMembroPerdedor;
    private Random random = new Random();

    // Filas dos dois lados - LinkedList
    private LinkedList<Guerreiro> Lado1 = new LinkedList<>();
    private LinkedList<Guerreiro> Lado2 = new LinkedList<>();

    // Lista para rastrear Goblins Comuns mortos (para ressurreição)
    private LinkedList<Goblins.GoblinComum> goblinsComunsMortos = new LinkedList<>();

    public void carregarDados() {
        Lado1 = LeitorDeArquivos.lerArquivo("src/Principal/orcs_goblins.txt", true);
        Lado2 = LeitorDeArquivos.lerArquivo("src/Principal/elfos_anoes.txt", false);
    }

    private void verificarMortos() {
        // verifica primeiro do Lado1
        if (!Lado1.isEmpty() && !Lado1.getFirst().estaVivo()) {
            Guerreiro morto = Lado1.getFirst();
            ultimoGuerreiroMorto = morto;
            matouUltimo = Lado2.isEmpty() ? null : Lado2.getFirst();

            // Se for Goblin Comum, adiciona à lista de mortos
            if (morto instanceof Goblins.GoblinComum goblinComum) {
                goblinsComunsMortos.add(goblinComum);
            }

            // Se for Rei Goblin, ativa habilidade de ressurreição
            if (morto instanceof Goblins.ReiGoblin) {
                // Ressuscita todos os Goblins Comuns mortos
                for (int i = 0; i < goblinsComunsMortos.size(); i++) {
                    Goblins.GoblinComum goblin = goblinsComunsMortos.get(i);
                    goblin.recuperarEnergia(50);
                    Lado1.addLast(goblin); // Adiciona no final da fila
                }
                goblinsComunsMortos.clear(); // Limpa a lista
            }

            // Se for Soldado com Warg, libera o Warg
            if (morto instanceof Orcs.OrcSoldado soldado) {
                if (soldado.getMontaria() != null && !soldado.getMontaria().estaMorto()) {
                    Orcs.Warg warg = soldado.liberarMontaria();
                    Lado1.addLast(warg); // Adiciona Warg no final da fila
                }
            }

            // Se for Glutão com Porco, libera o Porco
            if (morto instanceof Anoes.AnaoGlutao glutao) {
                if (glutao.getMontaria() != null && !glutao.getMontaria().estaMorto()) {
                    Anoes.PorcoDeGuerra porco = glutao.liberarMontaria();
                    Lado2.addLast(porco); // Adiciona Porco no final da fila
                }
            }

            Lado1.removeFirst();

            if (Lado1.isEmpty() && !Lado2.isEmpty()) {
                ultimoMembroPerdedor = ultimoGuerreiroMorto;
            }
            return;
        }

        // verifica primeiro do Lado2
        if (!Lado2.isEmpty() && !Lado2.getFirst().estaVivo()) {
            Guerreiro morto = Lado2.getFirst();
            ultimoGuerreiroMorto = morto;
            matouUltimo = Lado1.isEmpty() ? null : Lado1.getFirst();

            // Se for Glutão com Porco, libera o Porco
            if (morto instanceof Anoes.AnaoGlutao glutao) {
                if (glutao.getMontaria() != null && !glutao.getMontaria().estaMorto()) {
                    Anoes.PorcoDeGuerra porco = glutao.liberarMontaria();
                    Lado2.addLast(porco); // Adiciona Porco no final da fila
                }
            }

            Lado2.removeFirst();

            if (Lado2.isEmpty() && !Lado1.isEmpty()) {
                ultimoMembroPerdedor = ultimoGuerreiroMorto;
            }
        }
    }

    /**
     * Questão c) - Encontra e imprime o guerreiro mais velho
     */
    private void mostrarGuerreiroVelho() {
        // Começa com o primeiro do Lado1
        Guerreiro guerreiroVelho = Lado1.getFirst();//funcionaria com o lado1.get(0)

        // Percorre Lado1 procurando o mais velho
        for (int i = 0; i < Lado1.size(); i++) {
            // Obtém o elemento atual usando o índice 'i' e o método get()
            Guerreiro guerreiro = Lado1.get(i);
            if (guerreiro.getIdade() > guerreiroVelho.getIdade()) {
                guerreiroVelho = guerreiro;
            }
        }

        // Percorre Lado2 procurando o mais velho
        for (int i = 0; i < Lado2.size(); i++) {
            // Obtém o elemento atual usando o índice 'i' e o método get()
            Guerreiro guerreiro = Lado2.get(i);
            if (guerreiro.getIdade() > guerreiroVelho.getIdade()) {
                guerreiroVelho = guerreiro;
            }
        }

        System.out.println("\n IMPRESSÃO DO GUERREIRO MAIS VELHO ");
        System.out.println("c)");
        System.out.println("O " + guerreiroVelho.getTipoNome() + " "
                + guerreiroVelho.getNome() + " é o mais velho e tem "
                + guerreiroVelho.getIdade() + " anos");
    }
    
    private int somarPesoLista(LinkedList<Guerreiro> lista) {
        int soma = 0;

        for (int i = 0; i < lista.size(); i++) {
            soma += lista.get(i).getPeso();
        }

        return soma;
    }

    /**
     * Questão b) - Soma o peso total de cada lado
     */
    private void somarPesos() {
        int pesoLado1 = somarPesoLista(Lado1);
        int pesoLado2 = somarPesoLista(Lado2);

        System.out.println("\n IMPRESSÃO DOS PESOS DAS FILAS ");
        System.out.println("b)");
        System.out.println("    Orcs e Goblins pesam: " + pesoLado1);
        System.out.println("    Elfos e Anoes pesam: " + pesoLado2);
    }

    /**
     * Questão a) - Imprime todos os guerreiros de cada lado
     */
    private void printarLados() {
        System.out.println("\n IMPRESSÃO DAS FILAS ");
        System.out.println("a)");
        System.out.println("LADO 1 (Orcs e Goblins):");

        // Percorre e imprime cada guerreiro do Lado1
        for (int i = 0; i < Lado1.size(); i++) {
            Guerreiro guerreiro = Lado1.get(i);
            System.out.println("        " + guerreiro.getNome() + ", "
                    + guerreiro.getIdade() + " anos, "
                    + guerreiro.getPeso() + " kilos.");
        }

        System.out.println("\nLADO 2 (Elfos e Anões):");

        // Percorre e imprime cada guerreiro do Lado2 usando índice
        for (int i = 0; i < Lado2.size(); i++) {
            Guerreiro guerreiro = Lado2.get(i);
            System.out.println("        " + guerreiro.getNome() + ", "
                    + guerreiro.getIdade() + " anos, "
                    + guerreiro.getPeso() + " kilos.");
        }
    }

    //Método principal da batalha, simula rounds de ataque, mas tirei os prints
    public void batalhar() {

        // Continua enquanto ambos os lados têm guerreiros
        while ((!Lado1.isEmpty()) && (!Lado2.isEmpty())) {

            // dentro do while de batalhar(), por round:
            boolean primeiroLado1 = random.nextBoolean(); //A escolha é totalmente aleatória a cada round.

            if (primeiroLado1) {
                processarAtaque(Lado1, Lado2, true);   // primeiro ataque do round = true
                processarAtaque(Lado2, Lado1, false);  // segundo ataque do round
            } else {
                processarAtaque(Lado2, Lado1, true);
                processarAtaque(Lado1, Lado2, false);
            }

            //verifica se o ultimo está morto e remove
            verificarMortos();

            rotacionarFilas(Lado1);
            rotacionarFilas(Lado2);
        }
    }

    /**
     * Processa um ataque - o primeiro guerreiro de uma fila ataca o primeiro da
     * outra
     */
    private void processarAtaque(LinkedList<Guerreiro> atacantes,
            LinkedList<Guerreiro> alvos,
            boolean primeiroAtaqueDoRound) {
        if (atacantes.isEmpty() || alvos.isEmpty()) {
            return;
        }

        Guerreiro atacante = atacantes.getFirst();
        Guerreiro alvo = alvos.getFirst();

        // Se atacante estiver tonto, consome a tontura e não ataca
        if (atacante.isTonto()) {
            atacante.setTonto(false);
            return;
        }

        // Tratamento especial do Imperador: se for primeiro ataque do round e atacante for Imperador
        if (primeiroAtaqueDoRound && atacante instanceof Anoes.AnaoImperador) {
            // tira 50 de energia do alvo e tonteia (não permite contra-ataque)
            alvo.receberDano(50);
            alvo.setTonto(true);
            // após aplicar dano, verificar se o alvo morreu
            verificarMortos();
            return; // imperador consumiu a ação especial; o contra-ataque será impedido por isTonto()
        }

        // Se o alvo for Porco de Guerra, aplica praga no atacante
       if (alvo instanceof Anoes.PorcoDeGuerra porco) {
            int penalidade = porco.pragaDosPorcos(1);
            atacante.aplicarEfeitoPraga(penalidade / 5); 
        }

        // HABILIDADE ESPECIAL: Elfo Arqueiro ataca TODOS da fila inimiga
        if (atacante instanceof Elfos.ElfoArqueiro) {
            // Ataca todos os inimigos com 5 de dano
            for (int i = 0; i < alvos.size(); i++) {
                Guerreiro inimigoAtual = alvos.get(i);
                inimigoAtual.receberDano(5);
            }
            verificarMortos();
            return;
        }

        // Calcular dano considerando efeito da praga no atacante
        int danoBase = atacante.atacar();
        int danoReal = Math.max(0, danoBase - atacante.getEfeitoPraga());

        // HABILIDADE ESPECIAL: Warg ganha bônus por Wargs em sequência atrás dele
        if (atacante instanceof Orcs.Warg) {
            // Conta quantos Wargs estão em sequência atrás dele
            int bonus = 0;
            for (int i = 1; i < atacantes.size(); i++) {
                Guerreiro proximo = atacantes.get(i);
                if (proximo instanceof Orcs.Warg) {
                    bonus += 5;
                } else {
                    break; // Para quando não for mais Warg
                }
            }
            danoReal = 15 + bonus - atacante.getEfeitoPraga();
            if (danoReal < 0) {
                danoReal = 0;
            }
        }

        // HABILIDADE ESPECIAL: Soldado Orc montado ataca duas vezes (Warg + Soldado)
        if (atacante instanceof Orcs.OrcSoldado soldado) {
            if (soldado.wargPodeAtacar()) {
                // Primeiro ataca com o Warg
                int danoWarg = soldado.ataqueDoWarg();
                alvo.receberDano(danoWarg);
                verificarMortos();

                // Se o alvo morreu com o ataque do Warg, Soldado não ataca
                if (alvos.isEmpty() || !alvos.getFirst().estaVivo()) {
                    return;
                }
                // Se alvo ainda está vivo, Soldado ataca também
            }
        }

        // Guarda o ataque do alvo antes de morrer (para Açougueiro)
        int ataqueDoAlvo = alvo.atacar();

        // Aplica o dano normal
        alvo.receberDano(danoReal);

        // HABILIDADE ESPECIAL: Açougueiro devora inimigo e aumenta ataque
        if (atacante instanceof Orcs.OrcAcougueiro && alvo.estaMorto()) {
            Orcs.OrcAcougueiro acougueiro = (Orcs.OrcAcougueiro) atacante;
            // Aumenta o ataque baseado na força do inimigo morto
            acougueiro.aumentarAtaque(ataqueDoAlvo);
        }

        // HABILIDADE ESPECIAL: Senhor das Feras cria Warg ao matar inimigo
        if (atacante instanceof Orcs.OrcSenhorDasFeras && alvo.estaMorto()) {
            Orcs.OrcSenhorDasFeras senhor = (Orcs.OrcSenhorDasFeras) atacante;
            // Cria um novo Warg
            Orcs.Warg novoWarg = senhor.criarWarg();
            // Remove o Senhor do início
            atacantes.removeFirst();
            // Adiciona o Warg no final
            atacantes.addLast(novoWarg);
            // Adiciona o Senhor logo atrás do Warg
            atacantes.addLast(senhor);
        }

        // Depois do ataque, verificar se alguém morreu
        verificarMortos();
    }

    /**
     * Rotaciona a fila - remove o primeiro e adiciona no final É assim que a
     * LinkedList funciona como uma fila circular
     */
    private void rotacionarFilas(LinkedList<Guerreiro> lado) {
        if (lado.size() > 1) {
            Guerreiro primeiro = lado.removeFirst(); // Remove o primeiro
            lado.addLast(primeiro); // Adiciona no final
        }
    }

    /**
     * Remove um guerreiro de qualquer fila
     *
     * @param guerreiro param é usado para denotar uma descrição dos parâmetros
     */
    public void removeGuerreiro(Guerreiro guerreiro) {
        if (Lado1.contains(guerreiro)) {
            Lado1.remove(guerreiro);
        } else {
            Lado2.remove(guerreiro);
        }
    }

    public void mostrarUltimoGuerreiroMorto() {
        if (ultimoGuerreiroMorto == null || matouUltimo == null) {
            return;
        }

        System.out.println("\nÚLTIMO ATAQUE");
        System.out.println("f)");
        System.out.println(matouUltimo.getTipoNome() + " " + matouUltimo.getNome()
                + " matou " + ultimoGuerreiroMorto.getTipoNome() + " " + ultimoGuerreiroMorto.getNome());
    }


    /**
     * Questão e) - Mostra os dados do último membro do lado perdedor
     */
    private void mostrarUltimoMembroPerdedor() {
        if (ultimoMembroPerdedor != null) {
            System.out.println("\nÚLTIMO MEMBRO DO LADO PERDEDOR");
            System.out.println("e)");
            System.out.println("O último membro do lado perdedor foi: "
                    + ultimoMembroPerdedor.getNome() + ", "
                    + ultimoMembroPerdedor.getIdade() + " anos, "
                    + ultimoMembroPerdedor.getPeso() + " kilos.");
        }
    }

    /**
     * Método principal do jogo - executa tudo na ordem
     */
    public void jogo() {

        carregarDados();

        // Executa as questões a, b, c
        printarLados();
        somarPesos();
        mostrarGuerreiroVelho();

        // Executa a batalha
        batalhar();

        // Mostra o resultado
        mostrarUltimoGuerreiroMorto();
        terminarJogo();
        mostrarUltimoMembroPerdedor();
    }

    /**
     * Questão d) - Mostra qual lado venceu
     */
    private void terminarJogo() {
        System.out.println("\n\nFIM DO JOGO ");
        System.out.println("d)");

        if (Lado1.isEmpty() && Lado2.isEmpty()) {
            System.out.println("EMPATE! Todos morreram!");
        } else if (Lado1.isEmpty()) {
            System.out.println("Elfos e Anões VENCERAM!");
        } else if (Lado2.isEmpty()) {
            System.out.println("Orcs e Goblins VENCERAM!");
        }

    }

}
