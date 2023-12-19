package br.com.srbit;

public class Cartao {
    private String numero;
    private int numeroCliente;
    private double saldo;
    public Cartao(){}

    public Cartao(String numero, int numeroCliente, double saldo) {
        this.numero = numero;
        this.numeroCliente = numeroCliente;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
