package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;

import br.com.alura.screenmatch.excecao.ErroDeAnoException;
import br.com.alura.screenmatch.modelos.TituloRecord;
import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.*;


public class PrincipalBusca {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual filme deseja pesquisar: ");

        var escolha = scanner.nextLine();
//.replace(' ','+')+
        String endereco = "http://www.omdbapi.com/?t="+ URLEncoder.encode(escolha)+"&apikey=8133b1c1";

        System.out.println("ENDEREÇO: "+endereco);
        try {

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
        }catch (NumberFormatException e ){
            System.out.println("Ocorreu um erro: "+e.getMessage());
        }catch( IllegalArgumentException e){
            System.out.println("Algum erro de argumento na busca, verifique o endereço");
        }catch (ErroDeAnoException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Acabou tudo tranquilo");

    }
}