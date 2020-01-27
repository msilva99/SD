package Teste.jan_2020.Ex2;

// Java implementation for a client
// Save file as Client.java

import Teste.jan_2020.Ex1.Aeroporto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Client class
public class Client implements Runnable
{
    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    private Aeroporto a;

    // Constructor
    public Client(Socket socket, BufferedReader input, PrintWriter output, Aeroporto a)
    {
        this.socket = socket;
        this.input = input;
        this.output = output;
        this.a = a;
    }

    @Override
    public void run()
    {
        int pista = -1; // "Assumindo que o cliente sabe o que faz" >_<
        String received;
        String toreturn;
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

                    case "pedir para aterrar" :
                        toreturn = "Pista livre para aterrar: " + (pista = a.pedirParaAterrar());
                        break;

                    case "pedir para descolar" :
                        toreturn = "Pista livre para aterrar: " + (pista = a.pedirParaDescolar());
                        break;

                    case "aterrou" :
                        a.aterrou(pista);
                        toreturn = "Aviao aterrou!";
                        break;

                    case "descolou" :
                        a.descolou(pista);
                        toreturn = "Aviao descolou!";
                        break;

                    default:
                        toreturn = "?? O_o ??";
                        break;
                }

                output.println(toreturn);

            } catch (IOException | InterruptedException e) {
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