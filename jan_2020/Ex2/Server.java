package Teste.jan_2020.Ex2;

// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import Teste.jan_2020.Ex1.Aeroporto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Server class
public class Server
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);
        Aeroporto a = new Aeroporto(5);

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket socket = null;

            try
            {
                // socket object to receive incoming client requests
                socket = ss.accept();

                System.out.println("A new client is connected : " + socket);

                // obtaining input and out streams
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new Thread(new Client(socket, input, output, a));

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                socket.close();
                e.printStackTrace();
            }
        }
    }
}