package com.effective.mobile.task2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Stream;

public class Client implements AutoCloseable {
    private Socket client;
    private DataOutputStream os;
    private BufferedReader is;

    public Client(String host, int port) throws IOException {
        client = new Socket(host, port);
        os = new DataOutputStream(client.getOutputStream());
        is = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendReceive(String message) {
        try {
            os.writeBytes(message + "\n");
            os.flush();
            String responseLine = is.readLine();
            if (responseLine != null) {
                System.out.println("SERVER MESSAGE: " + responseLine);
            } else {
                System.out.println("NO MESSAGE FROM SERVER");
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() throws IOException {
        sendReceive("CLOSING CONNECTION...");
        is.close();
        os.close();
    }

    public static void main(String[] args) {
        int port = 8080;
        String host = "localhost";
        Stream.iterate(1, x -> x + 1).limit(5).forEach(id -> {
            new Thread(() -> {
                try (Client client = new Client(host, port)) {
                    client.sendReceive("HELLO FROM THREAD #" + Thread.currentThread().getId());
                    Thread.sleep(2000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}