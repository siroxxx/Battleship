package client;
import javax.swing.JFrame;
import java.awt.event.*;

public class MyFrame extends JFrame implements KeyListener {

    MyPanel panel;
    condivisa cond;

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Window");
        // this.setLocationRelativeTo(null);

        cond = new condivisa();

        panel = new MyPanel();
        
        this.addKeyListener(this);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        this.pack();
        this.setVisible(true);

        inizio();
    }

    private void inizio() {
        Fase1 f = new Fase1(cond);
        this.add(f);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
