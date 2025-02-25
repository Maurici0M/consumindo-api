package api.java.desafio;

import api.java.desafio.api.googlebooks.ApiGoogleBooks;
import api.java.desafio.api.viacep.ViaCep;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException {
        var viaCep = new ViaCep();
        viaCep.msgHome();

        //API GOOGLE BOOKS (Google Play Livros) - em produção
        var apiGoogle = new ApiGoogleBooks();
        //apiGoogle.msgHome();
    }
}
