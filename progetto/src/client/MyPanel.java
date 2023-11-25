package client;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

//classe panel che andrà poi ad estendere le classi delle fasi
public class MyPanel extends JPanel implements MouseInputListener, KeyListener {
    condivisa cond = new condivisa();

    public MyPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    // metodo che disegna la mappa inserita e la sposta a destra tanto quanto il
    // valore della variabile gap
    public void disegnaMappa(Graphics2D g2, int gap, Minimappa map) {
        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < Costanti.RIGHE; i++) {
                for (int k = 0; k < Costanti.COLONNE; k++) {
                    int initX = Costanti.MAP_X + (k * Costanti.WIDTH) + gap;
                    int initY = Costanti.MAP_Y + (i * Costanti.HEIGHT);

                    if (a == 0) {
                        int n = map.minimappa.get(i).get(k);

                        if (n == 0)
                            g2.setColor(Costanti.MAP_CYAN);
                        else if (n == -1)
                            g2.setColor(Color.WHITE);
                        else if (n == 2)
                            g2.setColor(Color.BLACK);
                        else if (n > 10) {
                            Font f2 = new Font("Congenial Black", Font.BOLD, 30 * Costanti.screenSize.width / 1920);
                            g2.setFont(f2);
                            int temp=n%10;
                            g2.drawString(temp.toString(), initX, initY);
                        } else
                            g2.setColor(Color.RED);

                    }

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

    // metodo per disegnare le navi prese dalla variabile 'listaNavi' dalla classe
    // 'condivisa.java'
    public void disegnaNavi(Graphics2D g2) {
        int greyTonality = 200;
        for (Nave nave : condivisa.listaNavi.flotta) {
            nave.colore = new Color(greyTonality, greyTonality, greyTonality);

            for (int i = 0; i < nave.lunghezza; i++) {
                g2.setColor(nave.colore);
                g2.fillRect(nave.coordinate.get(i).x, nave.coordinate.get(i).y,
                        (i == nave.lunghezza - 1 ? Costanti.SHIP_WIDTH : Costanti.WIDTH), Costanti.SHIP_HEIGHT);

                g2.setColor(Color.BLACK);
                g2.drawRect(nave.coordinate.get(i).x, nave.coordinate.get(i).y,
                        (i == nave.lunghezza - 1 ? Costanti.SHIP_WIDTH : Costanti.WIDTH), Costanti.SHIP_HEIGHT);

            }
            greyTonality -= 35;
        }
    }

    // metodo per disegnare la minimappa
    public void disegnaMinimappa(Graphics2D g2) {
        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < Costanti.RIGHE; i++) {
                for (int k = 0; k < Costanti.COLONNE; k++) {
                    int initX = Costanti.MINIMAP_X + (k * Costanti.MINIMAP_BLOCK_WIDTH);
                    int initY = Costanti.MINIMAP_Y + (i * Costanti.MINIMAP_BLOCK_HEIGHT);

                    if (a == 0) {
                        int n = condivisa.mappaDifesa.minimappa.get(i).get(k);

                        if (n == 0)
                            g2.setColor(new Color(30, 129, 176));
                        else if (n == -1)
                            g2.setColor(Color.RED);
                        else if (n == -2)
                            g2.setColor(Color.WHITE);
                        else if (n == -3)
                            g2.setColor(Color.BLACK);
                        else if (n > 10) {
                            Font f2 = new Font("Congenial Black", Font.BOLD, 20 * Costanti.screenSize.width / 1920);
                            g2.setFont(f2);
                            g2.drawString(Integer.toString(n % 10), initX, initY);
                        } else
                            g2.setColor(condivisa.listaNavi.flotta.get(n - 1).colore);

                        g2.fillRect(initX, initY, Costanti.MINIMAP_BLOCK_WIDTH, Costanti.MINIMAP_BLOCK_HEIGHT);
                    } else {
                        g2.drawRect(initX, initY, Costanti.MINIMAP_BLOCK_WIDTH, Costanti.MINIMAP_BLOCK_HEIGHT);
                        g2.drawRect(initX + 1, initY + 1, Costanti.MINIMAP_BLOCK_WIDTH - 2,
                                Costanti.MINIMAP_BLOCK_HEIGHT - 2);
                    }
                }
            }
            g2.setColor(Color.BLACK);
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
