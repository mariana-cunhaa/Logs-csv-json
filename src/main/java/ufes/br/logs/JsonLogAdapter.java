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
public class JsonLogAdapter implements ILogAdapter {
    private JsonFeitoEmCasa jsonLog;

    public JsonLogAdapter(String caminhoArquivo) {
        jsonLog = new JsonFeitoEmCasa(caminhoArquivo);
    }

    @Override
    public void gravar(String mensagem) {
        String[] arrayMensagem = mensagem.split(";");
        
        String operacao = arrayMensagem.length > 0 ? arrayMensagem[0] : "OPERACAO_DESCONHECIDA";
        String nome = arrayMensagem.length > 1 ? arrayMensagem[1] : "N/A";
        String data = arrayMensagem.length > 2 ? arrayMensagem[2] : "";
        String hora = arrayMensagem.length > 3 ? arrayMensagem[3] : "";
        String usuario = arrayMensagem.length > 4 ? arrayMensagem[4] : "Sistema";
        
        LocalDateTime dataHora;
        try {
            if (!data.isEmpty() && !hora.isEmpty()) {
                String[] partesData = data.split("-");
                String[] partesHora = hora.split(":");
                
                dataHora = LocalDateTime.of(
                    Integer.parseInt(partesData[0]), 
                    Integer.parseInt(partesData[1]), 
                    Integer.parseInt(partesData[2]), 
                    Integer.parseInt(partesHora[0]), 
                    Integer.parseInt(partesHora[1]), 
                    partesHora.length > 2 ? Integer.parseInt(partesHora[2]) : 0 
                );
            } else {
                dataHora = LocalDateTime.now();
            }
        } catch (Exception e) {
            dataHora = LocalDateTime.now();
        }
        
        String descricao = "Registro de Log do Sistema: " + nome;
        
        RegistroLog registroLog = new RegistroLog(dataHora, descricao, usuario, operacao);
        jsonLog.escrever(registroLog);
    }
}

