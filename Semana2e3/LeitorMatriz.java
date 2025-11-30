import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeitorMatriz {
    
    public static double[][] lerMatriz(String caminhoArquivo) throws IOException {
        
        List<double[]> linhas = new ArrayList<>();

        // Lê todas as linhas do arquivo
        List<String> conteudo = Files.lines(Paths.get(caminhoArquivo))
                             .skip(7)   // <-- pula as 7 primeiras linhas
                             .toList();;

        for (String linha : conteudo) {
            
            if (linha.trim().isEmpty()) continue; // ignora linhas vazias

            // Divide pelos espaços (1 ou mais)
            String[] partes = linha.trim().split("\\s+");

            double[] numeros = new double[partes.length];

            for (int i = 0; i < partes.length; i++) {
                numeros[i] = Double.parseDouble(partes[i]);
            }

            linhas.add(numeros);
        }

        // Converte lista para matriz
        double[][] matriz = new double[linhas.size()][];
        for (int i = 0; i < linhas.size(); i++) {
            matriz[i] = linhas.get(i);
        }

        return matriz;
    }

    public static void main(String[] args) throws Exception {
        String caminho = "D:\\AulasPos_Web\\Geo\\Semana2\\mdeAcima.txt";

        double[][] matriz = lerMatriz(caminho);

        // Exibe a matriz
        for (double[] linha : matriz) {
            for (double n : linha) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
