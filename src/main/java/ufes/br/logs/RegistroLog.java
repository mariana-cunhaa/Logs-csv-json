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
public class RegistroLog {
    private LocalDateTime dataHora;
    private String descricao;
    private String usuario;
    private String operacao;

    public RegistroLog(LocalDateTime dataHora, String descricao, String usuario, String operacao) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.usuario = usuario;
        this.operacao = operacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        return "RegistroLog{" +
                "dataHora=" + dataHora +
                ", descricao='" + descricao + '\'' +
                ", usuario='" + usuario + '\'' +
                ", operacao='" + operacao + '\'' +
                '}';
    }
}

