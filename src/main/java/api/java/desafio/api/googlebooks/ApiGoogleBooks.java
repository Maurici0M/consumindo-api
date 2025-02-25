package api.java.desafio.api.googlebooks;

import api.java.desafio.textcolors.AnsiColors;

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
    private AnsiColors ansiColors;

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

    public String apiKey(String keyApi){
        this.setKeyApi(keyApi);
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
                    .uri(URI.create(urlApi(apiKey(getKeyApi()), getBookName()))
                    ).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            this.response = response.body();
            System.out.println(getResponse());
        }
        catch (RuntimeException e) {
            System.out.println(getAnsiColors().textRed("Ops, ocorreu um erro: " + e.getMessage()));
            //throw new RuntimeException(e);
        }
    }

    public String encodedQuery(String query){
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        this.bookName = query;
        return encoded;
    }

    public void msgHome() throws IOException, InterruptedException {
        ansiColors();
        Scanner scInput = new Scanner(System.in);
        System.out.println(getAnsiColors().textMagenta("\nDigite o nome do livro que deseja: "));
        String enterNameBook = scInput.nextLine();
        setBookName(encodedQuery(enterNameBook));
        msgAlertApiKey();
        setKeyApi(scInput.nextLine());
        System.out.println(getAnsiColors().textCyan("\nOrganizando os \uD83D\uDCDA...\n"));
        apiConnection();
    }

    public void msgAlertApiKey(){
        ansiColors();
        System.out.println(
                getAnsiColors().
                        textBrilhante("\nPara sua requisição ser processada, será necessário adicionar a ")
                        +
                        getAnsiColors().textYellow("API KEY")
                        +
                        getAnsiColors().textBrilhante(" do GoogleBooks."));
        System.out.println("Por favor, " + getAnsiColors().textYellow("digite a API KEY para continuar: "));
    }

    // + ANSI COLORS
    public void ansiColors(){
        this.ansiColors = new AnsiColors();
    }

    public AnsiColors getAnsiColors() {
        return ansiColors;
    }

}
