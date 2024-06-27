package com.effective.mobile.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AutoCloseable {
    private final ServerSocket server;

    public Server(String host, int port, int backlogConnectionQueueLength) throws IOException {
        server = new ServerSocket(port, backlogConnectionQueueLength, InetAddress.getByName(host));
        System.out.println("Creating server...");
    }

    public void start() {
        System.out.println("Server is ready!");
        while (true) {
            acceptAndHandleClient(server);
        }
    }

    private void acceptAndHandleClient(ServerSocket server) {
        System.out.println("Waiting for incoming connections...");
        try (Socket clientSocket = server.accept()) {
            handleNewClient(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleNewClient(Socket clientSocket) throws IOException {
        System.out.println("Received connection from localhost and port " + clientSocket.getPort());
        BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintStream os = new PrintStream(clientSocket.getOutputStream());

        String line;
        while ((line = is.readLine()) != null) {
            if (line.equalsIgnoreCase("CLOSING CONNECTION..."))
                break;
            else {
                System.out.println(line);
                os.println(line);
                os.flush();
            }
        }
        System.out.println("SERVER RECEIVE MESSAGE : " + line);
        os.println("OK");
        os.flush();
        is.close();
        os.close();
    }

    public void close() throws IOException {
        server.close();
    }

    public static void main(String[] args) {
        try (Server server = new Server("localhost", 8080, 50)) {
            server.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
