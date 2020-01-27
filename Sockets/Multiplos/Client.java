package Teste.Sockets.Multiplos;

// Java implementation for a client
// Save file as Client.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Client class
public class Client implements Runnable
{
    final BufferedReader input;
    final PrintWriter output;
    final Socket socket;

    // Constructor
    public Client(Socket socket, BufferedReader input, PrintWriter output)
    {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run()
    {
        String received, toreturn;
        while (true)
        {
            try {
                // receive the answer from client
                received = input.readLine();

                if(received.equals(""))
                {
                    System.out.println("Closing this connection.");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                }

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "hello" :
                        toreturn = "Hello World!";
                        break;

                    case "poop" :
                        toreturn = "Unicorn Poop! <3";
                        break;

                    default:
                        toreturn = "?? O_o";
                        break;
                }

                output.println(toreturn);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.input.close();
            this.output.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
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
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}