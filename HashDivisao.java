public class HashDivisao implements FuncaoHash {
    @Override
    public int calcularHash(String codigo) {
        int valor = Integer.parseInt(codigo);
        return valor % 1000; // Ajuste o divisor conforme o tamanho da tabela
    }

    @Override
    public String getNome() {
        return "Divisão";
    }
}
