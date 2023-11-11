import javax.swing.JFrame;
import java.awt.event.*;

public class MyFrame extends JFrame implements KeyListener {

    MyPanel panel;

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Window");
        // this.setLocationRelativeTo(null);

        panel = new MyPanel();

        this.addKeyListener(this);

        this.add(panel);

        this.pack();
        this.setVisible(true);
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
