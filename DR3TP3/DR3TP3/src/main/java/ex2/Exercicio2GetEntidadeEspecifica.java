package ex2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio2GetEntidadeEspecifica {

    public static void main(String[] args) {
        for (int id = 1; id <= 8; id++) {
            getEntidadePorId(id);
            System.out.println("--------------------------------------------------");
        }
    }

    public static void getEntidadePorId(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int statusCode = conexao.getResponseCode();
            System.out.println("ID: " + id + " | Código de Status HTTP: " + statusCode);

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
            } else {
                System.out.println("Falha ao obter entidade com ID " + id);
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao conectar para ID " + id);
            e.printStackTrace();
        }
    }
}

