package client;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

    MyPanel panel;
    condivisa cond;

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Window");
        // this.setLocationRelativeTo(null);

        cond = new condivisa();

        panel = new MyPanel();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        this.pack();
        this.setVisible(true);

        inizio();
    }

    private void inizio() {
        Fase1 f = new Fase1();
        this.add(f);
    }
}
