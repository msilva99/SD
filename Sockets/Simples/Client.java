package Teste.Sockets.Simples;

import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket;
    private BufferedReader terminal;
    private BufferedReader input;
    private PrintWriter output;

    // constructor to put ip address and port
    public Client(String address, int port) throws IOException
    {
        // establish a connection
        socket = new Socket(address, port);
        System.out.println("Connected");

        terminal = new BufferedReader(new InputStreamReader(System.in));
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);

        // string to read message from input
        String line;

        // keep reading until "" is input
        while (!(line = terminal.readLine()).equals(""))
        {
                output.println(line);
                line = input.readLine();
                System.out.println(line);
        }

        // close the connection
            terminal.close();
            input.close();
            output.close();
            socket.close();
        }

    public static void main(String args[]) throws IOException
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}