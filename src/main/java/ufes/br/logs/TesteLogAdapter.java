package ufes.br.logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * @author marianacunha
 */
public class TesteLogAdapter {
    
    public static void main(String[] args) {
        
        // Teste 1: JsonLogAdapter
        testarJsonLogAdapter();
        
        // Teste 2: CSVLogAdapter
        testarCSVLogAdapter();
    }
    
    private static void testarJsonLogAdapter() {
        System.out.println("Teste 1: JsonLogAdapter");
        
        try {
            ILogAdapter logJson = new JsonLogAdapter("./logs/teste_json.json");
            
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            String data = agora.format(dateFormatter);
            String hora = agora.format(timeFormatter);
            
            // Teste 1: Log de LOGIN
            String mensagem1 = String.format("LOGIN;Gabriel Guimarães;%s;%s;gabriel.guimaraes", data, hora);
            logJson.gravar(mensagem1);
            System.out.println("Log JSON gravado: " + mensagem1);
            
            // Teste 2: Log de CADASTRO
            String mensagem2 = String.format("CADASTRO_USUARIO;Mariana Cunha;%s;%s;admin", data, hora);
            logJson.gravar(mensagem2);
            System.out.println("Log JSON gravado: " + mensagem2);
            
            // Teste 3: Log de ERRO
            String mensagem3 = String.format("ERRO_EXCLUSAO;Pedro Costa;%s;%s;admin", data, hora);
            logJson.gravar(mensagem3);
            System.out.println("Log JSON gravado: " + mensagem3);
            
            System.out.println("JsonLogAdapter: OK\n");
            
        } catch (Exception e) {
            System.err.println("JsonLogAdapter: FALHOU");
            e.printStackTrace();
        }
    }
    
    private static void testarCSVLogAdapter() {
        System.out.println("Teste 2: CSVLogAdapter");
        
        try {
            ILogAdapter logCsv = new CSVLogAdapter("./logs/teste_csv.csv", ";");
            
            // Teste 1: Log simples
            logCsv.gravar("LOGIN realizado com sucesso por Gabriel Guimarães");
            System.out.println("Log CSV gravado: LOGIN realizado com sucesso");
            
            // Teste 2: Log de operação
            logCsv.gravar("CADASTRO de novo usuário: Mariana Cunha");
            System.out.println("Log CSV gravado: CADASTRO de novo usuário");
            
            // Teste 3: Log de erro
            logCsv.gravar("ERRO ao excluir usuário: Permissão negada");
            System.out.println("Log CSV gravado: ERRO ao excluir usuário");
            
            System.out.println("CSVLogAdapter: OK\n");
            
        } catch (Exception e) {
            System.err.println("CSVLogAdapter: FALHOU");
            e.printStackTrace();
        }
    }
}

