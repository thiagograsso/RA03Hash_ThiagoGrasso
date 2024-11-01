import java.io.FileWriter;
import java.io.IOException;

public class TesteTabelasHash {
    public static void main(String[] args) throws IOException {
        int[] tamanhos = {1000, 10000, 100000};
        int[] quantidades = {1000000, 5000000, 20000000};
        FuncaoHash[] funcoesHash = {
                new HashDivisao(100000),
                new HashMultiplicacao(100000),
                new HashDobramento(100000)
        };
        boolean[] modos = {true, false}; // Encadeamento e Rehashing Linear

        GeradorDados gerador = new GeradorDados(42); // Seed fixo para dados aleatórios

        try (FileWriter writer = new FileWriter("resultados_tabela_hash.txt")) {
            for (int quantidade : quantidades) {
                Registro[] registros = gerador.gerarDados(quantidade);

                for (int tamanho : tamanhos) {
                    for (FuncaoHash funcao : funcoesHash) {
                        for (boolean encadeamentoSeparado : modos) {
                            TabelaHash tabela = new TabelaHash(tamanho, funcao, encadeamentoSeparado);

                            long tempoInsercao = System.currentTimeMillis();
                            int totalComparacoesInsercao = 0;
                            for (Registro reg : registros) {
                                totalComparacoesInsercao += tabela.inserir(reg);
                            }
                            tempoInsercao = System.currentTimeMillis() - tempoInsercao;

                            long tempoBusca = System.currentTimeMillis();
                            int totalComparacoesBusca = 0;
                            for (Registro reg : registros) {
                                totalComparacoesBusca += tabela.buscar(reg.getCodigo());
                            }
                            tempoBusca = System.currentTimeMillis() - tempoBusca;

                            writer.write(String.format(
                                    "Tamanho: %d, Quantidade: %d, Hash: %s, Encadeamento: %s\n" +
                                            "Tempo Inserção: %d ms, Comparações Inserção: %d\n" +
                                            "Tempo Busca: %d ms, Comparações Busca: %d\n\n",
                                    tamanho, quantidade, funcao.getClass().getSimpleName(),
                                    encadeamentoSeparado ? "Sim" : "Não",
                                    tempoInsercao, totalComparacoesInsercao,
                                    tempoBusca, totalComparacoesBusca
                            ));
                        }
                    }
                }
            }
        }
        System.out.println("Teste concluído. Resultados salvos em resultados_tabela_hash.txt.");
    }
}
