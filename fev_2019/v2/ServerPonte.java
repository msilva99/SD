package Teste.fev_2019.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPonte
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(5056);
        Ponte p = new Ponte();

        while (true)
        {
            Socket socket = ss.accept();

            System.out.println("A new client is connected : " + socket);

            Thread t = new Thread(new ClientHandler(p, socket));
            t.start();

        }
    }
}
