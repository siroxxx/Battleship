package client;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// classe dove viene creato il frame principale su cui vengono poi stampati i vari pannelli
public class MyFrame extends JFrame {

    MyPanel panel;// pannello
    condivisa cond;// classe condivisa
    static FasePreparazione fasePrep;// fase di preparazione
    static FaseAttDif faseAttDif;// fase di attacco

    MyFrame() throws IOException {

        // comandi base per la finestra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Battaglia Navale");
        this.setLocationRelativeTo(null);

        cond = new condivisa();

        panel = new MyPanel();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);// tolgo la barra per poter chiudere o
        // ridimensionare la pagina
        this.pack();
        this.setVisible(true);

        inizio();
    }

    // creo la fase di preparazione e la aggiungo al frame
    private void inizio() throws IOException {
        fasePrep = new FasePreparazione();
        this.add(fasePrep);

    }

    // rimuovo il pannello della fase di preparazione
    public void fineFase1() {
        getContentPane().remove(fasePrep);
        validate();
    }

    public void faseAttDif() {
        // faccio partire la fase di attacco
        fineFase1();
        faseAttDif = new FaseAttDif();
        this.add(faseAttDif);

        // refresho la pagina
        SwingUtilities.updateComponentTreeUI(this);
    }
}
