public class HashDobramento implements FuncaoHash {
    private int tamanho;

    public HashDobramento(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int calcularHash(String codigo) {
        int hash = 0;
        for (int i = 0; i < codigo.length(); i += 2) {
            String part = codigo.substring(i, Math.min(i + 2, codigo.length()));
            hash += Integer.parseInt(part);
        }
        return hash % tamanho;
    }
}
