package Teste.fev_2019.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class ClientHandler implements Runnable
{
    private Ponte p;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientHandler(Ponte p, Socket socket) throws IOException
    {
        this.p = p;
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run()
    {
        String received, to_return;
        while(true)
        {
            try
            {
                received = input.readLine();
                if (received.equals(""))
                {
                    socket.close();
                    System.out.println("Connection closed");
                    break;
                }

                String[] comando = received.split(" ");
                switch (comando[0])
                {
                    case "barco":
                    {
                        Thread t = new Thread(new Barco(p, parseInt(comando[1])));
                        t.start();
                        t.join();
                        to_return = "Ok";
                        break;
                    }
                    case "carro":
                    {
                        Thread t = new Thread(new Carro(p, parseInt(comando[1])));
                        t.start();
                        t.join();
                        to_return = "Ok";
                        break;
                    }
                    default:
                    {
                        to_return = "?? O_o ??";
                        break;
                    }
                }
                output.println(to_return);
            }
            catch (IOException | InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            // closing resources
            input.close();
            output.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}