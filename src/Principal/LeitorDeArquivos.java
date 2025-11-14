package Principal;

import Anoes.AnaoGlutao;
import Anoes.PorcoDeGuerra;
import Anoes.AnaoImperador;
import Elfos.*;
import Goblins.*;
import Orcs.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Classe responsável por ler os arquivos de texto e criar os guerreiros. Usa
 * BufferedReader para ler linha por linha FileReader e BufferedReader são
 * classes do Java usadas para ler arquivos de texto, mas funcionam em camadas
 * diferentes. O FileReader lê o arquivo caractere por caractere, enquanto o
 * BufferedReader usa o FileReader para ler dados do arquivo de forma mais
 * eficiente, armazenando-os em um buffer para otimizar a leitura de grandes
 * volumes de dados. PQ NÃO SE USA FILE INPUT STREAM? PQ ELE NÃO CODIFICA
 * CARACTERE!!
 */
public class LeitorDeArquivos {

    /**
     * Lê um arquivo e retorna uma LinkedList de guerreiros.
     *
     * @param nomeArquivo - nome do arquivo
     * @param lado1 - true se for lado1 (Orcs/Goblins), false se for lado2
     * (Elfos/Anões)
     * @return
     */
    public static LinkedList<Guerreiro> lerArquivo(String nomeArquivo, boolean lado1) {
        LinkedList<Guerreiro> guerreiros = new LinkedList<>();

        // Converte boolean para int (1 = lado1, 2 = lado2)
        int lado = lado1 ? 1 : 2;

        // Lê o arquivo e monta a lista de guerreiros
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) { //classe de entrada de texto em buffer em Java que lê caracteres de um fluxo para fornecer leitura eficiente de caracteres, arrays e strings
            String linha = br.readLine();

            while (linha != null) {
                // Separa a string em partes (limite é o espaço)
                String[] dados = linha.split(" ");

                int tipo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                int peso = Integer.parseInt(dados[3]);

                // Verifica se tem montaria (5º elemento = "Sim" ou "Não")
                boolean temMontaria = false;
                if (dados.length > 4) {
                    temMontaria = dados[4].equalsIgnoreCase("Sim"); //Character.toLowerCase(Character.toUpperCase(int))
                }

                Guerreiro guerreiro = criarGuerreiro(lado, tipo, nome, idade, peso, temMontaria);
                guerreiros.add(guerreiro);

                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + nomeArquivo);
        }

        return guerreiros;
    }

    /**
     * Cria um guerreiro baseado no lado e tipo
     */
    private static Guerreiro criarGuerreiro(int lado, int tipo, String nome, int idade,
            int peso, boolean temMontaria) {
        switch (lado) {
            case 1: // Lado 1: Orcs e Goblins
                switch (tipo) {
                    case 1:
                        return new GoblinComum(nome, idade, peso);
                    case 2:
                        return new ReiGoblin(nome, idade, peso);
                    case 3:
                        return new OrcSoldado(nome, idade, peso, temMontaria);
                    case 4:
                        OrcAcougueiro acougueiro = new OrcAcougueiro(nome, idade, peso, temMontaria);
                        acougueiro.inicializarMontaria();
                        return acougueiro;
                    case 5:
                        return new Warg(nome, idade, peso);
                    case 6:
                        return new OrcSenhorDasFeras(nome, idade, peso);
                    default:
                        throw new IllegalArgumentException("Tipo de guerreiro inválido");
                }

            case 2: // Lado 2: Elfos e Anões
                switch (tipo) {
                    case 1:
                        return new ElfoGuardiao(nome, idade, peso, temMontaria);
                    case 2:
                        return new ElfoArqueiro(nome, idade, peso, temMontaria);
                    case 3:
                        return new ElfoSuperior(nome, idade, peso, temMontaria);
                    case 4:
                        return new AnaoGlutao(nome, idade, peso, temMontaria);
                    case 5:
                        return new PorcoDeGuerra(nome, idade, peso);
                    case 6:
                        return new AnaoImperador(nome, idade, peso, temMontaria);
                    default:
                        throw new IllegalArgumentException("Tipo de guerreiro inválido");
                }

            default:
                return null;
        }
    }
}
