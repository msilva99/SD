package Teste.fev_2019.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 5056);
        BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String to_send = terminal.readLine();
            output.println(to_send);
            if (to_send.equals("")) {
                socket.close();
                System.out.println("Bye!");
                break;
            }
            System.out.println(input.readLine());
        }

        terminal.close();
        input.close();
        output.close();
    }
}