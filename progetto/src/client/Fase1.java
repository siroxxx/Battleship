package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Fase1 extends MyPanel{
    condivisa cond;

    public Fase1(condivisa cond) {
        this.cond = cond;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        disegnaMappa(g2, 100);

        int fontSize = cond.FONT;
        Font f = new Font("Berlin Sans FB", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("INSERISCI LE TUE NAVI:", cond.WIDTH/2, cond.HEIGHT);
    } 
}
