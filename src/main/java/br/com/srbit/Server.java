package br.com.srbit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static Map<String, Cartao> cartoes = new HashMap<>();
    private static int NSUCounter = 1;

    public static void main(String[] args) {
        cartoes.put("1111", new Cartao("1111", 1, 1000.0));
        cartoes.put("2222", new Cartao("2222", 2, 2000.0));
        cartoes.put("3333", new Cartao("3333", 3, 3000.0));

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Aguardando conexão");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente: " + clientSocket.getInetAddress());
                Thread clientThread = new Thread(new ClienteHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static synchronized int generateNSU() {
        return NSUCounter++;
    }

    private static synchronized boolean validateTransaction(Transacao transacao) {
        Cartao cartao = cartoes.get(transacao.getFormaPagamento());
        if (cartao == null) {
            transacao.setCodigoResposta("05");
            transacao.setNSU(0);
            return false;
        }
        if (cartao.getSaldo() < transacao.getValor()) {
            transacao.setCodigoResposta("51");
            transacao.setNSU(0);
            return false;
        }
        return true;
    }

    private static synchronized void processTransaction(Transacao transacao) {
        Cartao cartao = cartoes.get(transacao.getFormaPagamento());
        if (cartao != null) {
            double novoSaldo = cartao.getSaldo() - transacao.getValor();
            cartao.setSaldo(novoSaldo);
            int novoNSU = generateNSU();
            transacao.setNSU(novoNSU);
            transacao.setCodigoResposta("00");
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket client;
        public ClienteHandler(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            try (
                    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(client.getInputStream())
            ) {
                while (true) {
                    Transacao transacao = (Transacao) in.readObject();
                    if (validateTransaction(transacao)) {
                        processTransaction(transacao);
                        out.writeObject(transacao);
                    } else {
                        out.writeObject(transacao);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
