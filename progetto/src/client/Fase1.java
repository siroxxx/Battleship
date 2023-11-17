package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class Fase1 extends MyPanel{
    private Point initialPoint = null;
    private Nave initialNave = null;

    public Fase1(){
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, 100);

        int fontSize = Costanti.FONT;
        Font f = new Font("Berlin Sans FB", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("INSERISCI LE TUE NAVI:", Costanti.WIDTH/2, Costanti.HEIGHT);

        disegnaNavi(g2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (cond.nave != -1) {
            if (initialPoint == null)
                initialPoint = e.getPoint();

            boolean isFirstTouch = false;
            for (int i = 0; i < cond.listaNavi.flotta.get(cond.nave - 1).coordinate.size(); i++){
                Point coord = cond.listaNavi.flotta.get(cond.nave - 1).coordinate.get(i);

                if (initialNave == null) {
                    isFirstTouch = true;
                    initialNave = new Nave(cond.listaNavi.flotta.get(cond.nave - 1).lunghezza, -1);
                }
                
                if (isFirstTouch) {
                    initialNave.coordinate.get(i).setLocation(coord.getX(), coord.getY());
                }

                coord.x = e.getX() - (int)(initialPoint.getX() - initialNave.coordinate.get(i).getX());
                coord.y = e.getY() - (int)(initialPoint.getY() - initialNave.coordinate.get(i).getY());
            }

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (initialPoint != null && initialNave != null){
            initialPoint = null;
            initialNave = null;
        }
        if (cond.nave != -1)
            cond.nave = -1;
    }
}
