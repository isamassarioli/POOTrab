package Principal;

/**
 * Classe principal que executa o jogo de batalha entre guerreiros
 */
public class Main {
    public static void main(String[] args) {
        // Cria a arena
        // Os arquivos são carregados automaticamente no bloco static da Arena
        Arena arena = new Arena();

        // Executa o jogo completo
        // Isso vai executar todas as questões (a, b, c, d, f) e a batalha
        arena.jogo();
    }
}
