public class HashMultiplicacao implements FuncaoHash {
    @Override
    public int calcularHash(String codigo) {
        double A = 0.6180339887; // Constante de Knuth
        int valor = Integer.parseInt(codigo);
        return (int) Math.floor(1000 * (valor * A % 1)); // Ajuste o multiplicador conforme o tamanho da tabela
    }

    @Override
    public String getNome() {
        return "Multiplicação";
    }
}
