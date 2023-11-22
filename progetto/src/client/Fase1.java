package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Fase1 extends MyPanel{
    private Point initialPoint = null;
    private Nave initialNave = null;
    private int mapGap = 100;

    public Fase1(){
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, mapGap);

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
                    initialNave = new Nave(cond.listaNavi.flotta.get(cond.nave - 1));
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
            checkPos();

            initialPoint = null;
            initialNave = null;
        }
        if (cond.nave != -1)
            cond.nave = -1;
    }

    public void checkPos() {
        Nave nave = cond.listaNavi.flotta.get(initialNave.numero - 1);
        Nave naveCopia = new Nave(nave);
        Rectangle naveRect = cond.getRectangle(naveCopia);
        Rectangle mapRect = new Rectangle(Costanti.MAP);
        mapRect.x += mapGap;

        if (mapRect.contains(naveRect)) {
            int gapX = Costanti.MAP_X + mapGap;
            int gapY = Costanti.MAP_Y;

            Point quadranteCoord = new Point();

            quadranteCoord.x = ((int)((naveCopia.coordinate.get(0).x - gapX) / Costanti.WIDTH)) * Costanti.WIDTH + gapX;
            quadranteCoord.y = ((int)((naveCopia.coordinate.get(0).y - gapY) / Costanti.HEIGHT)) * Costanti.HEIGHT + gapY;

            Point newCoord = new Point();
            for (int i = 0; i < nave.coordinate.size(); i++) {
                Point p = nave.coordinate.get(i);

                if(i == 0)
                    newCoord = new Point(nave.coordinate.get(i + 1).x - p.x, nave.coordinate.get(i + 1).y - p.y);

                p.x = quadranteCoord.x;
                p.y = quadranteCoord.y;

                quadranteCoord.x += newCoord.x;
                quadranteCoord.y += newCoord.y;
            }
        } else
            nave.resettaNave();

        repaint();
    }
}
