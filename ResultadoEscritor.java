import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultadoEscritor {
    public static void escreverResultado(String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultado_tabela_hash.txt", true))) {
            writer.write(conteudo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
