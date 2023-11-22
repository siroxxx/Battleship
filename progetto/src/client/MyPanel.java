package client;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class MyPanel extends JPanel implements MouseInputListener, KeyListener {
    Condivisa cond = new Condivisa();

    public MyPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void disegnaMappa(Graphics2D g2, int gap) {
        g2.setColor(new Color(30, 129, 176));

        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < Costanti.RIGHE; i++) {
                for (int k = 0; k < Costanti.COLONNE; k++) {
                    int initX = Costanti.MAP_X + (k * Costanti.WIDTH) + gap;
                    int initY = Costanti.MAP_Y + (i * Costanti.HEIGHT);

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

    // mettere switch-case in base alla rotazione della nave
    public void disegnaNavi(Graphics2D g2) {
        for (Nave nave : Condivisa.listaNavi.flotta) {
            for (int i = 0; i < nave.lunghezza; i++) {
                g2.setColor(Color.GRAY);
                g2.fillRect(nave.coordinate.get(i).x, nave.coordinate.get(i).y,
                        (i == nave.lunghezza - 1 ? Costanti.SHIP_WIDTH : Costanti.WIDTH), Costanti.SHIP_HEIGHT);

                g2.setColor(Color.BLACK);
                g2.drawRect(nave.coordinate.get(i).x, nave.coordinate.get(i).y,
                        (i == nave.lunghezza - 1 ? Costanti.SHIP_WIDTH : Costanti.WIDTH), Costanti.SHIP_HEIGHT);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
