package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

public class RandomTurno {
    Condivisa c;
    Random r;
    ThreadUtilities t;
    ThreadSocket g1, g2;

    public RandomTurno(Condivisa cond, ThreadUtilities t, ThreadSocket g1, ThreadSocket g2) {
        c = cond;
        r = new Random();
        this.t = t;
        this.g1 = g1;
        this.g2 = g2;
    }

    public void impostaTurni() { // randomizzo i turni iniziali: se 0 g1 attacca, se 1 g2 attacca
        Integer rand = r.nextInt(0, 2);
        if (rand == 0) { // g1 attacca g2 è in difesa
            c.turno = true;
        } else { // g2 attacca g1 è in difesa
            c.turno = false;
        }

        String daInviareAlClient = "";

        if (c.turno == true) {
            daInviareAlClient = "attacco;";
            c.rispostaDaInoltrare = "difesa;";
        } else {
            daInviareAlClient = "difesa;";
            c.rispostaDaInoltrare = "attacco;";
        }

        OutputStream out1 = new OutputStream() {

            @Override
            public void write(int b) throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }

        };
        try {
            out1 = g1.s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter w = new PrintWriter(out1, true);

        OutputStream out2 = new OutputStream() {

            @Override
            public void write(int b) throws IOException {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }

        };
        // Invio la risposta per far capire al client
        w.println(daInviareAlClient);
        try {
            out2 = g2.s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter w2 = new PrintWriter(out2, true);

        // Invio la risposta per far capire al client
        w2.println(c.rispostaDaInoltrare);
    }
}
