package ex6;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio6GetEntidadeCriada {

    public static void main(String[] args) {
        getEntidadePorId(11);
    }

    public static void getEntidadePorId(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int statusCode = conexao.getResponseCode();
            System.out.println("Código de Status HTTP: " + statusCode);

            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                String linha;
                StringBuilder resposta = new StringBuilder();

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha).append("\n");
                }

                leitor.close();
                System.out.println("Resposta:");
                System.out.println(resposta.toString());

            } else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println("Entidade com ID " + id + " não encontrada (404 Not Found).");
            } else {
                System.out.println("Erro inesperado: " + statusCode);
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor.");
            e.printStackTrace();
        }
    }
}
