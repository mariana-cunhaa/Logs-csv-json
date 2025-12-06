package ufes.br.logs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marianacunha
 */
class JsonFeitoEmCasa {
    private String caminhoArquivo;

    public JsonFeitoEmCasa(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        criarArquivoSeNaoExistir();
    }

    private void criarArquivoSeNaoExistir() {
        try {
            File arquivo = new File(caminhoArquivo);
            File diretorio = arquivo.getParentFile();
            
            if (diretorio != null && !diretorio.exists()) {
                diretorio.mkdirs();
            }
            
            if (!arquivo.exists()) {
                arquivo.createNewFile();
                try (FileWriter writer = new FileWriter(arquivo)) {
                    writer.write("[]");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de log JSON: " + e.getMessage());
        }
    }

    public void escrever(RegistroLog registro) {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            String conteudoAtual = new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
            JsonArray arrayLogs;
            
            if (conteudoAtual.trim().isEmpty() || conteudoAtual.trim().equals("[]")) {
                arrayLogs = new JsonArray();
            } else {
                arrayLogs = JsonParser.parseString(conteudoAtual).getAsJsonArray();
            }

            arrayLogs.add(gson.toJsonTree(registro));

            try (FileWriter writer = new FileWriter(caminhoArquivo)) {
                gson.toJson(arrayLogs, writer);
            }

        } catch (IOException e) {
            System.err.println("Erro ao escrever log JSON: " + e.getMessage());
        }
    }
}

