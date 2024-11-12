package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import br.com.alura.screenmatch.modelos.TituloRecord;
import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.*;


public class PrincipalBusca {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual filme deseja pesquisar: ");

        var escolha = scanner.nextLine();
        
        String endereco = "http://www.omdbapi.com/?t="+escolha+"&apikey=8133b1c1";

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = cliente
                .send(request, BodyHandlers.ofString());

                System.out.println(response.body());
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .create();

                String json = response.body();

                TituloRecord record = gson.fromJson(json, TituloRecord.class);

                Titulo meuTitulo = new Titulo(record);
                System.out.println(meuTitulo.toString());

    }
}