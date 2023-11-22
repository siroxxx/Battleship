package client;

import java.io.IOException;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    MyPanel panel;
    Condivisa cond;
    static FasePreparazione fase1;
    static FaseAttacco fA;

    MyFrame() throws IOException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Window");
        // this.setLocationRelativeTo(null);

        cond = new Condivisa();

        panel = new MyPanel();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // this.setUndecorated(true);
        this.pack();
        this.setVisible(true);

        inizio();

        AspettaFine aF = new AspettaFine();
        aF.start();
        try {
            aF.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fineFase1();
        fA = new FaseAttacco();
        this.add(fA);

        // TODO: Collegare la socket e iniziare la partita
    }

    private void inizio() throws IOException {
        fase1 = new FasePreparazione();
        Condivisa.stato = 1;
        this.add(fase1);
    }

    public void fineFase1() {
        getContentPane().remove(fase1);
        validate();
    }
}
