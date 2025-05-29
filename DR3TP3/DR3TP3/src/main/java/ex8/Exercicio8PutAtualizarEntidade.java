package ex8;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio8PutAtualizarEntidade {

    public static void main(String[] args) {
        int id = 10;
        String jsonAtualizado = "{\"name\": \"atualizado\"}";

        atualizarEntidadeComPUT(id, jsonAtualizado);
        verificarAtualizacao(id);
    }

    public static void atualizarEntidadeComPUT(int id, String json) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("PUT");
            conexao.setRequestProperty("Content-Type", "application/json");
            conexao.setDoOutput(true);

            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeBytes(json);
            saida.flush();
            saida.close();

            int statusCode = conexao.getResponseCode();
            System.out.println("PUT - Código de Status HTTP: " + statusCode);

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha).append("\n");
            }

            leitor.close();
            System.out.println("Resposta do servidor ao PUT:");
            System.out.println(resposta.toString());

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao atualizar a entidade com PUT.");
            e.printStackTrace();
        }
    }

    public static void verificarAtualizacao(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int statusCode = conexao.getResponseCode();
            System.out.println("GET - Código de Status HTTP: " + statusCode);

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha).append("\n");
            }

            leitor.close();
            System.out.println("Conteúdo da entidade após PUT:");
            System.out.println(resposta.toString());

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao buscar a entidade após PUT.");
            e.printStackTrace();
        }
    }
}

