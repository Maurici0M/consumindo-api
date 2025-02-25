package api.java.desafio.api.viacep;

import api.java.desafio.api.viacep.record.RecordViaCep;
import api.java.desafio.textcolors.AnsiColors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViaCep {
    private List<RecordViaCep> listaDeCeps = new LinkedList<>();
    private String cep;
    private String urlApi;
    private String responseApi;
    private AnsiColors ansiColors;

    //GETTERS E SETTERS
    public void setResponseApi(String responseApi) {
        this.responseApi = responseApi;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String setUrlApi(String urlApi) {
        this.urlApi = urlApi;
        return urlApi;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public String getCep() {
        return cep;
    }

    public String urlApiCriada(String cep) {
        this.setCep(cep);
        return this.setUrlApi("https://viacep.com.br/ws/" + getCep() +"/json/");
    }

    public String getResponseApi() {
        return responseApi;
    }

    //METODOS
    public void apiConnection() throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApiCriada(getCep()))
                    ).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            setResponseApi(response.body());

            //LOG - verificar body da requisição
            //System.out.println(getResponseApi());
        }

        catch (RuntimeException e) {
            System.out.println("Houve um erro ao processar a requisição: " + e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    public String encodedQuery(String query){
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        this.cep = query;
        return encoded;
    }

    public Gson gson(){
        Gson gson;
        return gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void processarRespostaApi(){
        try {
            ansiColors();
            RecordViaCep viaCep = gson().fromJson(getResponseApi(), RecordViaCep.class);
            String viaCepJson = gson().toJson(viaCep);

            //add cep a lista
            this.listaDeCeps.add(viaCep);

            System.out.println(getAnsiColors().textGreen("\nResultados para seu CEP: ") + viaCepJson);
        }
        catch (NumberFormatException e) {
            System.out.println("\nHouve um erro: " + e.getMessage());
        }
        finally {
            System.out.println("\nRequisição processada! ");
        }
    }

    public void fileWriter() throws IOException {
        FileWriter arquivoDeCeps = new FileWriter("DbCeps.json");
        arquivoDeCeps.write(gson().toJson(listaDeCeps));
        arquivoDeCeps.close();
    }

    public void msgHome() throws IOException, InterruptedException {
        Scanner scInput = new Scanner(System.in);

        while (true) {
            //inicialize o ansiColors para evitar travamento do código
            ansiColors();

            System.out.println( getAnsiColors().textGreen("\nDigite o CEP que deseja buscar ") + getAnsiColors().textYellow("(caso queira sair, digite 1 ou sair)"));
            String enterCep = scInput.nextLine();
            setCep(encodedQuery(enterCep));

            //while = false
            if (getCep().equalsIgnoreCase("sair") || getCep().equalsIgnoreCase("1")){
                System.out.println(getAnsiColors().textRed("\nVocê escolheu sair! ") + getAnsiColors().textBrilhante("Obrigado por utilizar o buscador de CEP!"));
                System.out.println(getAnsiColors().textMagenta("\nDados obtidos da API ViaCep"));
                break;
            }

            else if (getCep().length() >= 7) {
                urlApiCriada(getCep());
                System.out.println(getAnsiColors().textBlue("\nProcurando por você... \uD83C\uDF0E"));
                System.out.println("URL da requisição: " + getUrlApi());
                apiConnection();
                processarRespostaApi();
            }

            else {
                System.out.println(getAnsiColors().textRed("\nOps, não entendi o seu comando. Vamos tentar novamente? "));
                System.out.println(getAnsiColors().textBrilhante("Digite um CEP válido, por exemplo: 66063060"));
            }

            /*catch (IllegalStateException e) {
                    System.out.println(getAnsiColors().textRed("\nHum... houve um erro no sistema!"));
                    System.out.println(getAnsiColors().textYellow("Por enquanto, somente consigo encontrar o seu endereço pelo  número CEP. Vamos tentar novamente?"));
                    //throw new IllegalStateException(e);
                }*/
        }

        System.out.println(getAnsiColors().textBlue("\nCEPS salvos: ") + gson().toJson(listaDeCeps));
        fileWriter();
    }

    // + ANSI COLORS
    public void ansiColors(){
        this.ansiColors = new AnsiColors();
    }

    public AnsiColors getAnsiColors() {
        return ansiColors;
    }

}
