package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class FasePreparazione extends MyPanel {
    private Point initialPoint = null;
    private Nave initialNave = null;
    private int mapGap = Costanti.WIDTH;
    private int numRotazione;

    public FasePreparazione() {

        // TODO: aggiungere i pulsanti
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, mapGap);

        int fontSize = Costanti.FONT;
        Font f = new Font("Berlin Sans FB", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("INSERISCI LE TUE NAVI:", Costanti.WIDTH / 2, Costanti.HEIGHT);

        disegnaNavi(g2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (cond.nave != -1) {
            if (initialPoint == null)
                initialPoint = e.getPoint();

            boolean isFirstTouch = false;
            for (int i = 0; i < Condivisa.listaNavi.flotta.get(cond.nave - 1).coordinate.size(); i++) {
                Point coord = Condivisa.listaNavi.flotta.get(cond.nave - 1).coordinate.get(i);

                if (initialNave == null) {
                    isFirstTouch = true;
                    initialNave = new Nave(Condivisa.listaNavi.flotta.get(cond.nave - 1));
                    numRotazione = initialNave.numero;
                }

                if (isFirstTouch) {
                    initialNave.coordinate.get(i).setLocation(coord.getX(), coord.getY());
                }

                coord.x = e.getX() - (int) (initialPoint.getX() - initialNave.coordinate.get(i).getX());
                coord.y = e.getY() - (int) (initialPoint.getY() - initialNave.coordinate.get(i).getY());
            }

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (initialPoint != null && initialNave != null) {
            setPosizione(Condivisa.listaNavi.flotta.get(initialNave.numero - 1));

            initialPoint = null;
            initialNave = null;
        }
        if (cond.nave != -1)
            cond.nave = -1;

        // codice finché non vanno i pulsanti
        if (e.getButton() == 3) {// va alla fase di attacco
            for (Nave n : Condivisa.listaNavi.flotta)
                for (Point p : n.coordinate)
                    p.x -= mapGap;

            Condivisa.stato = -1;
        }

        if (numRotazione != 0) {
            Nave nave = Condivisa.listaNavi.flotta.get(numRotazione - 1);
            if (e.getButton() == 4) {// gira a sx
                nave.ruotaNave((-1));
                setPosizione(nave);
            }
            if (e.getButton() == 5) {// gira a dx
                nave.ruotaNave((1));
                setPosizione(nave);
            }
        }
        repaint();
    }

    public void setPosizione(Nave nave) {
        Nave naveCopia = new Nave(nave);
        Rectangle mapRect = new Rectangle(Costanti.MAP);
        mapRect.x += mapGap;

        int gapX = Costanti.MAP_X + mapGap;
        int gapY = Costanti.MAP_Y;

        Point quadranteCoord = new Point();

        quadranteCoord.x = ((int) ((naveCopia.coordinate.get(0).x - gapX) / Costanti.WIDTH)) * Costanti.WIDTH
                + gapX;
        quadranteCoord.y = ((int) ((naveCopia.coordinate.get(0).y - gapY) / Costanti.HEIGHT)) * Costanti.HEIGHT
                + gapY;

        Point newCoord = new Point();
        for (int i = 0; i < nave.coordinate.size(); i++) {
            Point p = nave.coordinate.get(i);

            if (i == 0)
                newCoord = new Point(nave.coordinate.get(i + 1).x - p.x, nave.coordinate.get(i + 1).y - p.y);

            p.x = quadranteCoord.x;
            p.y = quadranteCoord.y;

            quadranteCoord.x += newCoord.x;
            quadranteCoord.y += newCoord.y;
        }

        if (!checkOverlaps(Condivisa.getRectangle(nave), nave.numero))
            nave.resettaNave();
        else if (!mapRect.contains(Condivisa.getRectangle(nave)))
            nave.resettaNave();
        else
            nave.isSet = true;

        repaint();
    }

    public boolean checkOverlaps(Rectangle r, int num) {
        for (Nave n : Condivisa.listaNavi.flotta)
            if (n.numero != num) {
                Rectangle rect = Condivisa.getRectangle(n);
                if (rect.x < r.x + r.width && rect.x + rect.width > r.x && rect.y < r.y + r.height
                        && rect.y + rect.height > r.y)
                    return false;
            }

        return true;
    }

    // non funziona
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);

        switch (e.getKeyCode()) {
            case Costanti.LEFT:
                Condivisa.listaNavi.flotta.get(numRotazione - 1).ruotaNave(-1);
                break;

            case Costanti.RIGHT:
                Condivisa.listaNavi.flotta.get(numRotazione - 1).ruotaNave(1);
                break;
            case Costanti.ENTER:
                Condivisa.stato = -1;
                break;
        }
    }
}
