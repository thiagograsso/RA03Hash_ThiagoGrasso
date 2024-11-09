import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnaliseTabelaHash {
    public static void main(String[] args) {
        int[] tamanhosTabela = {10, 100, 1000, 10000, 100000};
        int[] tamanhosDados = {1000000, 5000000, 20000000};
        FuncaoHash[] funcoesHash = {new HashMultiplicacao(), new HashDivisao(), new HashDobramento()};

        for (int tamanho : tamanhosTabela) {
            for (int quantidadeDados : tamanhosDados) {
                List<Long> temposInsercao = new ArrayList<>();
                List<Integer> colisoesInsercao = new ArrayList<>();
                List<Long> temposBusca = new ArrayList<>();
                List<Integer> comparacoesBusca = new ArrayList<>();

                for (FuncaoHash funcaoHash : funcoesHash) {
                    TabelaHash tabelaHash = new TabelaHash(tamanho, funcaoHash);
                    Random random = new Random(123); // Seed fixa para consistência nos dados

                    // Medição de inserção
                    long tempoInicioInsercao = System.nanoTime();
                    for (int i = 0; i < quantidadeDados; i++) {
                        int valor = random.nextInt(quantidadeDados);
                        tabelaHash.inserir(valor);
                    }
                    long tempoFimInsercao = System.nanoTime();
                    temposInsercao.add(tempoFimInsercao - tempoInicioInsercao);
                    colisoesInsercao.add(tabelaHash.getContagemColisoes());

                    // Medição de busca (5 buscas aleatórias)
                    long tempoTotalBusca = 0;
                    int comparacoesTotais = 0;
                    for (int j = 0; j < 5; j++) {
                        int valorBusca = random.nextInt(quantidadeDados);
                        long tempoInicioBusca = System.nanoTime();
                        tabelaHash.buscar(String.valueOf(valorBusca));
                        long tempoFimBusca = System.nanoTime();

                        tempoTotalBusca += (tempoFimBusca - tempoInicioBusca);
                        comparacoesTotais += tabelaHash.getContagemComparacoes();
                    }
                    temposBusca.add(tempoTotalBusca / 5); // Média de tempo de busca
                    comparacoesBusca.add(comparacoesTotais / 5); // Média de comparações de busca

                    tabelaHash.resetarContadores(); // Reseta contadores para próxima função/tamanho
                }

                // Exibir resultados no console ou salvar em arquivo
                exibirResultados(tamanho, quantidadeDados, funcoesHash, temposInsercao, colisoesInsercao, temposBusca, comparacoesBusca);
            }
        }
    }

    private static void exibirResultados(int tamanhoTabela, int quantidadeDados, FuncaoHash[] funcoesHash, List<Long> temposInsercao, List<Integer> colisoesInsercao, List<Long> temposBusca, List<Integer> comparacoesBusca) {
        System.out.println("\nTabela de Resultados para Tamanho da Tabela: " + tamanhoTabela + " e Quantidade de Dados: " + quantidadeDados);
        System.out.printf("%-20s %-15s %-15s %-15s %-15s\n", "Função Hash", "Tempo Inserção", "Colisões", "Tempo Busca", "Comparações");

        for (int i = 0; i < funcoesHash.length; i++) {
            System.out.printf("%-20s %-15d %-15d %-15d %-15d\n", funcoesHash[i].getNome(), temposInsercao.get(i), colisoesInsercao.get(i), temposBusca.get(i), comparacoesBusca.get(i));
        }
    }
}
