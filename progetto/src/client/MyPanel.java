package client;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class MyPanel extends JPanel implements MouseInputListener{
    condivisa cond = new condivisa();
    
    public MyPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
    }
    
    public void disegnaMappa(Graphics2D g2, int gap) {
        g2.setColor(new Color(30,129,176));

        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < Costanti.RIGHE; i++) {
                for (int k = 0; k < Costanti.COLONNE; k++) {
                    int initX = ((this.getSize().width - Costanti.MAP_WIDTH)/2) + (k * Costanti.WIDTH) + gap;
                    int initY = ((this.getSize().height - Costanti.MAP_HEIGHT)/2) + (i * Costanti.HEIGHT);

                    if (a == 0)
                        g2.fillRect(initX, initY, Costanti.WIDTH, Costanti.HEIGHT);
                    else {
                        g2.drawRect(initX, initY, Costanti.WIDTH, Costanti.HEIGHT);
                        g2.drawRect(initX + 1, initY + 1, Costanti.WIDTH - 2, Costanti.HEIGHT - 2);
                    }
                }
            }
            g2.setColor(Color.BLACK);
        }
    }

    //mettere switch-case in base alla rotazione della nave
    public void disegnaNavi(Graphics2D g2) {
        g2.setColor(Color.GREEN);

        //Random rand = new Random();
        for (Nave nave : cond.listaNavi.flotta) {
            for (int i = 0; i < nave.lunghezza; i++) {
                /*float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                g2.setColor(randomColor);*/
                g2.fillRect(nave.coordinate.get(i).x, nave.coordinate.get(i).y, (i == nave.lunghezza - 1 ? Costanti.WIDTH / 4 * 3 : Costanti.WIDTH), Costanti.HEIGHT / 4 * 3);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cond.getNave(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
