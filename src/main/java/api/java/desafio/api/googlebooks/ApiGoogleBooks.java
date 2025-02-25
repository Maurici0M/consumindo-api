package api.java.desafio.api.googlebooks;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ApiGoogleBooks {
    private String bookName;
    private String urlApi;
    private String keyApi;
    private String response;

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String setUrlApi(String urlApi) {
        this.urlApi = urlApi;
        return getUrlApi();
    }

    public void setKeyApi(String keyApi) {
        this.keyApi = keyApi;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public String getKeyApi() {
        return keyApi;
    }

    public String getBookName() {
        return bookName;
    }

    public String getResponse() {
        return response;
    }

    public String apiKey(){
        this.setKeyApi("AIzaSyCP9KIAFEJconUeKpkx0uo1JjMZeHfdWeQ");
        return getKeyApi();
    }

    public String urlApi (String key, String bookName) {
        this.setKeyApi(key);
        this.setBookName(bookName);
        return this.setUrlApi("https://www.googleapis.com/books/v1/volumes?q=" + getBookName() + "&key=" + getKeyApi() +"&country=BR");
    }

    public void apiConnection() throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApi(apiKey(), getBookName()))
                    ).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            this.response = response.body();
            System.out.println(getResponse());
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String encodedQuery(String query){
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        this.bookName = query;
        return encoded;
    }

    public String textYellow(String txt){
        return "\n\u001B[33m" + txt + "\u001B[0m \n";
    }

    public void msgHome() throws IOException, InterruptedException {
        Scanner scInput = new Scanner(System.in);
        System.out.println("\nDigite o nome do livro que deseja: ");
        String enterNameBook = scInput.nextLine();
        setBookName(encodedQuery(enterNameBook));
        //System.out.println(getBookName());
        System.out.println(textYellow("Organizando os \uD83D\uDCDA..."));
        apiConnection();
    }
}
