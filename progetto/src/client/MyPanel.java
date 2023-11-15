package client;
import java.awt.*;

import javax.swing.*;

public class MyPanel extends JPanel{
    condivisa cond = new condivisa();

    public MyPanel() {
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g) {
    }
    
    public void disegnaMappa(Graphics2D g2) {
        g2.setColor(new Color(30,129,176));
        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < Costanti.righe; i++) {
                for (int k = 0; k < Costanti.colonne; k++) {
                    int initX = ((this.getSize().width - cond.MAP_WIDTH)/2) + (k * cond.WIDTH);
                    int initY = ((this.getSize().height - cond.MAP_HEIGHT)/2) + (i * cond.HEIGHT);

                    if (a == 0)
                        g2.fillRect(initX, initY, cond.WIDTH, cond.HEIGHT);
                    else {
                        g2.drawRect(initX, initY, cond.WIDTH, cond.HEIGHT);
                        g2.drawRect(initX + 1, initY + 1, cond.WIDTH - 2, cond.HEIGHT - 2);
                    }
                }
            }
            g2.setColor(Color.BLACK);
        }
    }

}