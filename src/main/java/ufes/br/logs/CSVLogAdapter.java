package ufes.br.logs;

import java.time.LocalDateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marianacunha
 */
public class CSVLogAdapter implements ILogAdapter {
    private CSVWriter csvWriter;
    private String delimitador;

    public CSVLogAdapter(String caminhoArquivo, String delimitador) {
        csvWriter = new CSVWriter(caminhoArquivo);
        this.delimitador = delimitador;
        csvWriter.setDelimiter(delimitador);
    }

    @Override
    public void gravar(String mensagem) {
        csvWriter.write(mensagem, LocalDateTime.now());
    }
}

