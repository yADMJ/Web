package ex4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Exercicio4GetComParametros {

    public static void main(String[] args) {
        try {
            String categoria = "teste";
            int limite = 5;

            String parametros = String.format("categoria=%s&limite=%d",
                    URLEncoder.encode(categoria, StandardCharsets.UTF_8),
                    limite);

            String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
            String urlComParametros = baseUrl + "?" + parametros;

            System.out.println("URL Final Montada: " + urlComParametros);

            URL url = new URL(urlComParametros);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int statusCode = conexao.getResponseCode();
            System.out.println("Código de Status HTTP: " + statusCode);

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha).append("\n");
            }
            leitor.close();

            System.out.println("Resposta do servidor:");
            System.out.println(resposta.toString());

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao conectar com o servidor.");
            e.printStackTrace();
        }
    }
}
