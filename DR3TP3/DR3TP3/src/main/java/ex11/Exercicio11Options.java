package ex11;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Exercicio11Options {

    public static void main(String[] args) {
        try {
            String endpoint = "https://apichallenges.eviltester.com/sim/entities";
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("OPTIONS");

            conexao.connect();

            int statusCode = conexao.getResponseCode();
            System.out.println("OPTIONS - Código de Status HTTP: " + statusCode);

            String allowMethods = conexao.getHeaderField("Allow");
            if (allowMethods != null) {
                System.out.println("Métodos HTTP permitidos (Allow): " + allowMethods);
            } else {
                System.out.println("Cabeçalho 'Allow' não encontrado.");
            }

            conexao.disconnect();

        } catch (IOException e) {
            System.out.println("Erro ao realizar requisição OPTIONS.");
            e.printStackTrace();
        }
    }
}

