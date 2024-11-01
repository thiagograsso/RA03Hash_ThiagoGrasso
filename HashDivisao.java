public class HashDivisao implements FuncaoHash {
    private int tamanho;

    public HashDivisao(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int calcularHash(String codigo) {
        int num = Integer.parseInt(codigo);
        return num % tamanho;
    }
}
