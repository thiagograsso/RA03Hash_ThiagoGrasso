import java.util.LinkedList;

public class TabelaHash {
    private Registro[] tabelaRehash; // Para rehashing linear
    private LinkedList<Registro>[] tabelaEncadeamento; // Para encadeamento separado
    private boolean encadeamentoSeparado;
    private FuncaoHash funcaoHash;

    public TabelaHash(int tamanho, FuncaoHash funcaoHash, boolean encadeamentoSeparado) {
        this.funcaoHash = funcaoHash;
        this.encadeamentoSeparado = encadeamentoSeparado;

        if (encadeamentoSeparado) {
            // Inicializa a tabela de encadeamento separado como um array de listas encadeadas
            tabelaEncadeamento = new LinkedList[tamanho];
            for (int i = 0; i < tamanho; i++) {
                tabelaEncadeamento[i] = new LinkedList<>();
            }
        } else {
            // Inicializa a tabela de rehashing linear como um array de Registros
            tabelaRehash = new Registro[tamanho];
        }
    }

    // Método inserir com tratamento de colisão e índice normalizado
    public int inserir(Registro registro) {
        int comparacoes = 0;
        int index = Math.abs(funcaoHash.calcularHash(registro.getCodigo())) % (encadeamentoSeparado ? tabelaEncadeamento.length : tabelaRehash.length);

        if (encadeamentoSeparado) {
            tabelaEncadeamento[index].add(registro);
        } else {
            // Rehashing linear para resolver colisões
            while (tabelaRehash[index] != null) {
                comparacoes++;
                index = (index + 1) % tabelaRehash.length; // Incremento circular para rehashing linear
            }
            tabelaRehash[index] = registro;
        }
        return comparacoes;
    }

    // Método buscar com normalização do índice e comparação correta
    public int buscar(String codigo) {
        int comparacoes = 0;
        int index = Math.abs(funcaoHash.calcularHash(codigo)) % (encadeamentoSeparado ? tabelaEncadeamento.length : tabelaRehash.length);

        if (encadeamentoSeparado) {
            for (Registro reg : tabelaEncadeamento[index]) {
                comparacoes++;
                if (reg.getCodigo().equals(codigo)) { // Comparação correta usando equals
                    return comparacoes;
                }
            }
        } else {
            while (tabelaRehash[index] != null) {
                comparacoes++;
                if (tabelaRehash[index].getCodigo().equals(codigo)) { // Comparação correta usando equals
                    return comparacoes;
                }
                index = (index + 1) % tabelaRehash.length; // Incremento circular para busca linear
            }
        }
        return -1; // Não encontrado
    }
}
