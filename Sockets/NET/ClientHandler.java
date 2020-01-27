package Teste.Sockets.NET;


import java.io.*;
import java.net.Socket;
import java.util.Date;

// ClientHandler class
class ClientHandler implements Runnable
{
    final BufferedReader input;
    final PrintWriter output;
    final Socket socket;


    // Constructor
    public ClientHandler(Socket socket, BufferedReader input, PrintWriter output)
    {
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run()
    {
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

//                // write on output stream based on the
//                // answer from the client
//                switch (received) {
//
//                    case "Date" :
//                        toreturn = fordate.format(date);
//                        dos.writeUTF(toreturn);
//                        break;
//
//                    case "Time" :
//                        toreturn = fortime.format(date);
//                        dos.writeUTF(toreturn);
//                        break;
//
//                    default:
//                        dos.writeUTF("Invalid input");
//                        break;
//                }

                toreturn = received;
                output.println("> " + toreturn);

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
}