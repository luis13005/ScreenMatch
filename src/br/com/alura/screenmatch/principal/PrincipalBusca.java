package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

public class PrincipalBusca {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual filme deseja pesquisar: ");

        var escolha = scanner.nextLine();
        

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.googleapis.com/auth/O Principe"))
                // .uri(URI.create("http://www.omdbapi.com/?t="+escolha+"&apikey=8133b1c1"))
                .build();

        HttpResponse<String> response = cliente
                .send(request, BodyHandlers.ofString());

                System.out.println(response.body());

    }
}