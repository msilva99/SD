package Teste.Sockets.Simples;


// A Java program for a Server

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    //initialize socket and input stream
    private Socket socket;
    private ServerSocket server;
    private BufferedReader input;
    private PrintWriter output;

    // constructor with port
    public Server(int port) throws IOException
    {
        // starts server and waits for a connection
        server = new ServerSocket(port);
        System.out.println("Server started");

        System.out.println("Waiting for a client ...");

        socket = server.accept();
        System.out.println("Client accepted");

        // takes input from the client socket
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        String line;

        // reads message from client until "Over" is sent
        while (!(line = input.readLine()).equals(""))
        {
            output.println("Server response: " + line);
        }
        System.out.println("Closing connection");

        // close connection
        socket.close();
        input.close();
        output.close();
    }

    public static void main(String args[]) throws IOException
    {
        Server server = new Server(5000);
    }
}