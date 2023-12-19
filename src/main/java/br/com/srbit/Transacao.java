package br.com.srbit;

import java.io.Serializable;

public class Transacao implements Serializable {
    private double valor;
    private String data;
    private String hora;
    private String redeTransmissora;
    private String formaPagamento;
    private int NSU;
    private String codigoResposta;
    public Transacao() {}
    public Transacao(double valor, String data, String hora, String redeTransmissora, String formaPagamento, int NSU, String codigoResposta) {
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.redeTransmissora = redeTransmissora;
        this.formaPagamento = formaPagamento;
        this.NSU = NSU;
        this.codigoResposta = codigoResposta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRedeTransmissora() {
        return redeTransmissora;
    }

    public void setRedeTransmissora(String redeTransmissora) {
        this.redeTransmissora = redeTransmissora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getNSU() {
        return NSU;
    }

    public void setNSU(int NSU) {
        this.NSU = NSU;
    }

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }
}
