public class TabelaHash {
    private int tamanho;
    private FuncaoHash funcaoHash;
    private int contagemColisoes;
    private int contagemComparacoes;
    

    public TabelaHash(int tamanho, FuncaoHash funcaoHash) {
        this.tamanho = tamanho;
        this.funcaoHash = funcaoHash;
        this.contagemColisoes = 0;
        this.contagemComparacoes = 0;
        
    }

    // Método de inserção para registro
    public int inserir(Registro registro) {
        int hash = funcaoHash.calcularHash(registro.getCodigo());
        // Código para inserir o registro na tabela com tratamento de colisões
        // Incrementa contagemColisoes se houver uma colisão
        return hash;
    }

    // Método de inserção para inteiro
    public int inserir(int valor) {
        String codigo = String.valueOf(valor);
        Registro registro = new Registro(codigo);
        return inserir(registro);
    }

    public int buscar(String codigo) {
        int hash = funcaoHash.calcularHash(codigo);

        // Código para busca na tabela com contagem de comparações

        return -1; // Retorna o índice ou -1 se não encontrar
    }

    public int getContagemColisoes() {
        return contagemColisoes;
    }

    public int getContagemComparacoes() {
        return contagemComparacoes;
    }

    public void resetarContadores() {
        contagemColisoes = 0;
        contagemComparacoes = 0;
    }
}
