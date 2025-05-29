package ex12;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiExperiments {

    private static final String BASE_URL = "https://apichallenges.eviltester.com/simpleapi/items";

    public static void main(String[] args) throws IOException {
        System.out.println("1. GET todos os itens:");
        getAllItems();

        System.out.println("\n2. GET ISBN aleatório:");
        String isbn = getRandomISBN();
        System.out.println("ISBN gerado: " + isbn);

        System.out.println("\n3. POST criar item:");
        criarItem(isbn, "Livro de Teste", "Aluno");

        System.out.println("\n4. PUT atualizar item:");
        atualizarItem(isbn, "Livro Atualizado", "Aluno Atualizado");

        System.out.println("\n5. DELETE remover item:");
        deletarItem(isbn);
    }

    private static void getAllItems() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();
        System.out.println("Status: " + status);
        printResponse(conn);

        conn.disconnect();
    }

    private static String getRandomISBN() throws IOException {
        URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();
        System.out.println("Status: " + status);

        String isbn = null;
        if (status == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            isbn = reader.readLine();
            reader.close();
        } else {
            System.out.println("Falha ao obter ISBN aleatório");
        }
        conn.disconnect();
        return isbn;
    }

    private static void criarItem(String isbn, String title, String author) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String json = String.format("{\"isbn\":\"%s\", \"title\":\"%s\", \"author\":\"%s\"}", isbn, title, author);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
            out.flush();
        }

        int status = conn.getResponseCode();
        System.out.println("Status: " + status);
        printResponse(conn);
        conn.disconnect();
    }

    private static void atualizarItem(String isbn, String title, String author) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String json = String.format("{\"isbn\":\"%s\", \"title\":\"%s\", \"author\":\"%s\"}", isbn, title, author);

        try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
            out.writeBytes(json);
            out.flush();
        }

        int status = conn.getResponseCode();
        System.out.println("Status: " + status);
        printResponse(conn);
        conn.disconnect();
    }

    private static void deletarItem(String isbn) throws IOException {
        URL url = new URL(BASE_URL + "/" + isbn);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int status = conn.getResponseCode();
        System.out.println("Status: " + status);
        printResponse(conn);
        conn.disconnect();
    }

    private static void printResponse(HttpURLConnection conn) throws IOException {
        BufferedReader reader;
        int status = conn.getResponseCode();

        if (status >= 200 && status < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}

