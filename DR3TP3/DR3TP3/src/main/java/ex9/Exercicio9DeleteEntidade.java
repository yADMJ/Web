package ex9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio9DeleteEntidade {

    public static void main(String[] args) {
        int id = 9;
        deletarEntidade(id);
        verificarSeFoiDeletada(id);
    }

    public static void deletarEntidade(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");

            int statusCode = conexao.getResponseCode();
            System.out.println("DELETE - Código de Status HTTP: " + statusCode);

            BufferedReader leitor = new BufferedReader(new InputStreamReader(
                    conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha).append("\n");
            }

            leitor.close();
            System.out.println("Resposta ao DELETE:");
            System.out.println(resposta.toString());

            conexao.disconnect();
        } catch (IOException e) {
            System.out.println("Erro ao deletar entidade.");
            e.printStackTrace();
        }
    }

    public static void verificarSeFoiDeletada(int id) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities/" + id;
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int statusCode = conexao.getResponseCode();
            System.out.println("GET após DELETE - Código de Status HTTP: " + statusCode);

            if (statusCode == 404) {
                System.out.println("Entidade " + id + " não encontrada (como esperado).");
            } else {
                BufferedReader leitor = new BufferedReader(new InputStreamReader(
                        conexao.getInputStream()));
                String linha;
                StringBuilder resposta = new StringBuilder();

                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha).append("\n");
                }

                leitor.close();
                System.out.println("Resposta inesperada da entidade:");
                System.out.println(resposta.toString());
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao verificar entidade após DELETE.");
            e.printStackTrace();
        }
    }
}

