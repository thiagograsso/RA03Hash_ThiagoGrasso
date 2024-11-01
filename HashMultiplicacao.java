public class HashMultiplicacao implements FuncaoHash {
    private int tamanho;

    public HashMultiplicacao(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public int calcularHash(String codigo) {
        double A = 0.6180339887; // constante de Knuth
        int num = Integer.parseInt(codigo);
        return (int) Math.floor(tamanho * (num * A % 1));
    }
}
