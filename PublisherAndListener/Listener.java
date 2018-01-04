// Listener.java
import java.io.*;
import java.net.*;

public class Listener {

    //indico la porta attraverso cui voglio comunicare
    public static final int PORT = 1050;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Server: Avviato. Socket info: " + serverSocket);

        Socket clientSocket = null;

        BufferedReader in = null;

        try {
            System.out.println("Server: Sono in attesa di un client");

            clientSocket = serverSocket.accept();

            System.out.println("Connessione accettata a: "+ clientSocket);

            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);

            System.out.println("Verranno ora trascritti i messaggi del client\n");

            //ciclo di ricezione dal client
            while (true) {
                String str = in.readLine();

                if (str.equals("END"))
                    break;

                System.out.println("Client: " + str);
            }
        }
        catch (IOException e) {
            System.err.println("Accettattazione fallita");
            System.exit(1);
        }

        // eseguo la chiusura di stream e socket
        in.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Server: Arresto completato");
    }
}

/* Server_Listener */