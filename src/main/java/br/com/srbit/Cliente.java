package br.com.srbit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 12345);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            while (true) {
                Transacao transacao = new Transacao();
                transacao.setValor(10.0);
                transacao.setData("2023-10-10");
                transacao.setHora("10:10");
                transacao.setRedeTransmissora("rede");
                transacao.setFormaPagamento("1111");
                transacao.setNSU(0);
                transacao.setCodigoResposta("");
                out.writeObject(transacao);

                Transacao resposta = (Transacao) in.readObject();

                if ("00".equals(resposta.getCodigoResposta())) {
                    System.out.println("Transação aprovada!");
                    System.out.println("NSU: " + resposta.getNSU() + "data: " + resposta.getData() + " hora: " + resposta.getHora());
                } else {
                    System.out.println("Erro na transação: Cod. " + resposta.getCodigoResposta() + "NSU:" + resposta.getNSU() );
                }

                Thread.sleep(5000);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}