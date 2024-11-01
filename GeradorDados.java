import java.util.Random;

public class GeradorDados {
    private Random random;

    public GeradorDados(int seed) {
        this.random = new Random(seed);
    }

    public Registro[] gerarDados(int quantidade) {
        Registro[] registros = new Registro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            String codigo = String.format("%09d", random.nextInt(1000000000));
            registros[i] = new Registro(codigo);
        }
        return registros;
    }
}
