package ex10;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio10DeleteInvalido {

    public static void main(String[] args) {
        int id = 2;
        deletarEntidadeProtegida(id);
    }

    public static void deletarEntidadeProtegida(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");

            int statusCode = conexao.getResponseCode();
            System.out.println("DELETE inválido - Código de Status HTTP: " + statusCode);

            BufferedReader leitor;
            if (statusCode >= 200 && statusCode <= 299) {
                leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            } else {
                leitor = new BufferedReader(new InputStreamReader(conexao.getErrorStream()));
            }

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
            System.out.println("Erro ao tentar deletar entidade protegida.");
            e.printStackTrace();
        }
    }
}
