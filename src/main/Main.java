package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonParser;
import models.Cep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String cep = "";
        boolean cepValido = false;

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (!cepValido) {
            System.out.println("Digite o CEP para consulta (somente os 8 números):");
            cep = scanner.nextLine().trim();

            if (cep.matches("[0-9]{8}")) {
                cepValido = true;
            } else {
                System.err.println("❌ Erro: O CEP deve conter exatamente 8 dígitos numéricos. Tente novamente.");
            }
        }

        String cepAPesquisar = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(cepAPesquisar))
                    .GET()
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            String json = response.body();

            if (status != 200) {
                System.err.println("❌ Erro HTTP: código " + status + ". Não foi possível consultar o CEP.");
                System.err.println("Resposta do servidor: " + json);
                return;
            }

            // Verifica se a API retornou {"erro": true} de forma segura via JSON
            JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
            if (jsonObj.has("erro") && jsonObj.get("erro").getAsBoolean()) {
                System.out.println("❌ Erro: CEP não encontrado pela ViaCEP.");
                return;
            }

            // Converte para objeto Cep (classe models.Cep)
            Cep cepObjeto = gson.fromJson(json, Cep.class);

            System.out.println("\n📄 Resultado da consulta:");
            System.out.println(cepObjeto);

            String nomeArquivo = cep + ".json";

            try (FileWriter escrita = new FileWriter(nomeArquivo)) {
                escrita.write(gson.toJson(cepObjeto));
                System.out.println("\n✅ Dados salvos com sucesso em: " + nomeArquivo);
            } catch (IOException e) {
                System.err.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("❌ Erro de I/O (Rede ou Arquivo). Verifique a conexão ou permissão de escrita.");
            System.err.println("Detalhe: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("❌ A operação foi interrompida.");
            System.err.println("Detalhe: " + e.getMessage());
            Thread.currentThread().interrupt(); // boa prática re-interromper a thread
        } catch (JsonSyntaxException e) {
            System.err.println("❌ Erro de sintaxe JSON. O formato da resposta da API está incorreto.");
            System.err.println("Detalhe: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
