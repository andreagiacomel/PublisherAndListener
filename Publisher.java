// Publisher.java

import java.net.*;
import java.io.*;

public class Publisher {

    public static void main(String[] args) throws IOException {

        //indirizzo del server
        InetAddress addr = InetAddress.getByName(null);

        Socket socket = null;
        BufferedReader stdIn= null;
        PrintWriter out = null;

        try {
            socket = new Socket(addr, Listener.PORT);
            System.out.println("Client: Avviato. Socket info: "+ socket);

            OutputStreamWriter osw = new OutputStreamWriter( socket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);

            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            System.out.println("E' ora possibile scrivere i messaggi per il server.\n");

            // ciclo di lettura da tastiera, invio al server
            while (true){
                userInput = stdIn.readLine();
                out.println(userInput);

                if (userInput.equals("END"))
                    break;
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Impossibile connettersi al server indicato: "+ addr);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Impossibile ottenere I/O dal server: " + addr);
            System.exit(1);
        }

        // eseguo la chiusura di stream e socket
        out.close();
        stdIn.close();
        socket.close();
        System.out.println("Client: Arresto completato");
    }
}

// Publisher