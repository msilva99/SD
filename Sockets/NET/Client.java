package Teste.Sockets.NET;

import java.io.*;
import java.net.*;

// Client class
public class Client
{
    public static void main(String[] args) throws IOException
    {
        // establish the connection with server port 5056
        Socket socket = new Socket("localhost", 5056);

        // obtaining input and out streams
        BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // the following loop performs the exchange of
        // information between client and client handler
        while (true)
        {
            String tosend = terminal.readLine();
            output.println(tosend);

            // If client sends exit,close this connection
            // and then break from the while loop
            if(tosend.equals(""))
            {
                System.out.println("Closing this connection: " + socket);
                socket.close();
                System.out.println("Connection closed");
                break;
            }

            // printing info sent by server
            System.out.println(input.readLine());
        }

        // closing resources
        terminal.close();
        input.close();
        output.close();
    }
}