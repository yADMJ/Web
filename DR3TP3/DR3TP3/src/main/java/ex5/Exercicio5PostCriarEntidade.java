package ex5;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio5PostCriarEntidade {

    public static void main(String[] args) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities";
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            String json = "{\"name\": \"aluno\"}";

            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeBytes(json);
            saida.flush();
            saida.close();

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

            if (resposta.toString().contains("\"id\":11")) {
                System.out.println("ID gerado: 11");
            } else {
                System.out.println("ID gerado identificado na resposta acima.");
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao realizar POST.");
            e.printStackTrace();
        }
    }
}

