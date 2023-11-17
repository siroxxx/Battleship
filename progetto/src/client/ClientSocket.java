package client;
import java.net.*;
import java.io.*;

//testo di base preso da google
public class ClientSocket {
    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName(Costanti.INDIRIZZO);
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, Costanti.PORTA_SERVER);

        try {
            System.out.println("socket = " + socket);

            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream()));
            PrintWriter out =
                new PrintWriter(
                    new BufferedWriter(
                        new OutputStreamWriter(
                            socket.getOutputStream())),true);

            for(int i = 0; i < 10; i ++) {
                out.println("ciao " + i);
                String str = in.readLine();
                System.out.println(str);
            }

            out.println("END");
            
            } finally {
                System.out.println("closing...");
                socket.close();
        }
    }
}