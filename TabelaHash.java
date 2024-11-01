import java.util.LinkedList;

public class TabelaHash {
    private LinkedList<Registro>[] tabelaEncadeamento;
    private Registro[] tabelaRehash;
    private FuncaoHash funcaoHash;
    private boolean encadeamentoSeparado; // Flag para escolher a técnica

    public TabelaHash(int tamanho, FuncaoHash funcaoHash, boolean encadeamentoSeparado) {
        this.funcaoHash = funcaoHash;
        this.encadeamentoSeparado = encadeamentoSeparado;

        if (encadeamentoSeparado) {
            tabelaEncadeamento = new LinkedList[tamanho];
            for (int i = 0; i < tamanho; i++) {
                tabelaEncadeamento[i] = new LinkedList<>();
            }
        } else {
            tabelaRehash = new Registro[tamanho];
        }
    }

    public int inserir(Registro registro) {
        int comparacoes = 0;
        int index = funcaoHash.calcularHash(registro.getCodigo());

        if (encadeamentoSeparado) {
            tabelaEncadeamento[index].add(registro);
        } else {
            while (tabelaRehash[index] != null) {
                comparacoes++;
                index = (index + 1) % tabelaRehash.length; // Rehashing linear
            }
            tabelaRehash[index] = registro;
        }
        return comparacoes;
    }

    public int buscar(String codigo) {
        int comparacoes = 0;
        int index = funcaoHash.calcularHash(codigo);

        if (encadeamentoSeparado) {
            for (Registro reg : tabelaEncadeamento[index]) {
                comparacoes++;
                if (reg.getCodigo().equals(codigo)) {
                    return comparacoes;
                }
            }
        } else {
            while (tabelaRehash[index] != null) {
                comparacoes++;
                if (tabelaRehash[index].getCodigo().equals(codigo)) {
                    return comparacoes;
                }
                index = (index + 1) % tabelaRehash.length;
            }
        }
        return -1; // Não encontrado
    }
}
