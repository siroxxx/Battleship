import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class testServer {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Modifica con l'indirizzo del tuo server
        int serverPort = 6321; // Modifica con la porta del tuo server

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            // Stream di input da server
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Stream di output verso il server
            PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);

            // Stream di input da console
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connesso al server. Inserisci i messaggi (\"exit\" per uscire):");

            String userInput;
            while (true) {
                // Leggi input da console
                userInput = consoleInput.readLine();

                // Invia il messaggio al server
                serverOutput.println(userInput);

                // Se l'utente vuole uscire, chiudi il client
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                // Leggi e visualizza la risposta dal server
                String serverResponse = serverInput.readLine();
                System.out.println("Risposta dal server: " + serverResponse);
            }

            // Chiudi la connessione
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
