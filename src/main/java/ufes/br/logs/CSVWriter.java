package ufes.br.logs;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marianacunha
 */

class CSVWriter {
    private String caminhoArquivo;
    private String delimitador;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public CSVWriter(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.delimitador = ";"; 
    }

    public void setDelimiter(String delimitador) {
        this.delimitador = delimitador;
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
                try (FileWriter writer = new FileWriter(arquivo);
                     CSVPrinter csvPrinter = new CSVPrinter(writer, 
                         CSVFormat.DEFAULT.builder()
                             .setDelimiter(delimitador)
                             .setHeader("DataHora", "Mensagem")
                             .build())) {
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de log CSV: " + e.getMessage());
        }
    }

    public void write(String mensagem, LocalDateTime dataHora) {
        if (delimitador == null) {
            delimitador = ";";
            criarArquivoSeNaoExistir();
        }
        
        try (FileWriter writer = new FileWriter(caminhoArquivo, true);
             CSVPrinter csvPrinter = new CSVPrinter(writer, 
                 CSVFormat.DEFAULT.builder()
                     .setDelimiter(delimitador)
                     .setSkipHeaderRecord(true)
                     .build())) {

            csvPrinter.printRecord(
                dataHora.format(FORMATTER),
                mensagem
            );

        } catch (IOException e) {
            System.err.println("Erro ao escrever log CSV: " + e.getMessage());
        }
    }
}

