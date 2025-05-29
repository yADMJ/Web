package ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio1GetEntidades {

    public static void main(String[] args) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities";
            URL url = new URL(endpoint);

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
            e.printStackTrace();
        }
    }
}
