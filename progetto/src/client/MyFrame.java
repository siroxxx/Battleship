package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// classe dove viene creato il frame principale su cui vengono poi stampati i vari pannelli
public class MyFrame extends JFrame {

    MyPanel panel;// pannello
    condivisa cond;// classe condivisa
    static FasePreparazione fasePrep;// fase di preparazione
    public static FaseAttDif faseAtt;// fase di attacco

    MyFrame() throws IOException {

        // comandi base per la finestra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Battaglia Navale");
        this.setLocationRelativeTo(null);

        cond = new condivisa();

        panel = new MyPanel();

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);// tolgo la barra per poter chiudere o ridimensionare la pagina
        this.pack();
        this.setVisible(true);

        //inizio();

        // inizio del thread
        AspettaFine aF = new AspettaFine();
        aF.start();
        try {
            aF.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // faccio partire la fase di attacco
        ClientSocket cs = new ClientSocket(this);
        fineFase1();
        faseAtt = new FaseAttDif();
        this.add(faseAtt);

        // refresho la pagina
        SwingUtilities.updateComponentTreeUI(this);

        // TODO: Collegare la socket e iniziare la partita

        this.addMouseListener( new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }
            
        });
    }

    // creo la fase di preparazione e la aggiungo al frame
    private void inizio() throws IOException {
        fasePrep = new FasePreparazione();
        condivisa.stato = 1;
        this.add(fasePrep);
    }

    // rimuovo il pannello della fase di preparazione
    public void fineFase1() {
        getContentPane().remove(fasePrep);
        validate();
    }
}
