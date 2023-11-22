package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FaseAttacco extends MyPanel {

    public FaseAttacco() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, 0);

        int fontSize = Costanti.FONT;
        Font f = new Font("Berlin Sans FB", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("SCEGLI DOVE ATTACCARE:", Costanti.WIDTH / 2, Costanti.HEIGHT);

        disegnaNavi(g2);
    }

}
