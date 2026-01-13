package com.alura.literalura.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumirAPI {

    public String obterDados(String endereco) {

        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() != 200) {
                System.out.println("Erro na API: Status " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}



//    public String obterDados(String endereco) {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(endereco))
//                .build();
//
//        HttpResponse<String> response = null;
//
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch ( IOException | InterruptedException e) {
//            throw  new RuntimeException(e);
//        }
//
//        String json = response.body();
//        return json;



