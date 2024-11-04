public class TesteTabelasHash {
    public static void main(String[] args) {
        TabelaHash tabelaHash = new TabelaHash(1000, new HashDivisao());

        // Inserção de exemplo
        for (int i = 0; i < 100; i++) {
            String codigo = String.format("%09d", i); // Gera códigos de 9 dígitos
            Registro registro = new Registro(codigo);
            int comparacoes = tabelaHash.inserir(registro); // Verifique se inserir aceita Registro
            ResultadoEscritor.escreverResultado("Comparações ao inserir " + codigo + ": " + comparacoes);
        }

        // Busca de exemplo
        for (int i = 0; i < 100; i++) {
            String codigo = String.format("%09d", i); // Códigos de 9 dígitos
            int comparacoesBusca = tabelaHash.buscar(codigo);
            ResultadoEscritor.escreverResultado("Comparações ao buscar " + codigo + ": " + comparacoesBusca);
        }

        System.out.println("Resultados gravados no arquivo resultado_tabela_hash.txt");
    }
}
