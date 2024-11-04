public class HashDobramento implements FuncaoHash {
    @Override
    public int calcularHash(String codigo) {
        int hash = 0;
        for (int i = 0; i < codigo.length(); i += 2) {
            String parte = codigo.substring(i, Math.min(i + 2, codigo.length()));
            hash += Integer.parseInt(parte);
        }
        return hash % 1000; // Ajuste o divisor conforme o tamanho da tabela
    }

    @Override
    public String getNome() {
        return "Dobramento";
    }
}
