package client;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Condivisa {
    public static int stato = 0; // -1: waiting; 0: prima fase; 1: fase d'attacco; 2: fase di difesa
    public static Flotta listaNavi = new Flotta();
    public int nave = -1;

    public Condivisa() {

    }

    public void getNave(Point point) {
        for (Nave n : listaNavi.flotta) {
            if (getRectangle(n).contains(point)) {
                nave = n.numero;
                break;
            }
        }
    }

    public static Rectangle getRectangle(Nave nave) {
        Point firstPoint = new Point(nave.coordinate.get(0));
        Point lastPoint = new Point(nave.coordinate.get(nave.lunghezza - 1));
        Rectangle r = new Rectangle();

        switch (nave.rotazione) {
            case 1:
            case -2:
                lastPoint.x += Costanti.SHIP_WIDTH;
                lastPoint.y += Costanti.SHIP_HEIGHT;

                r = new Rectangle(firstPoint, new Dimension(lastPoint.x - firstPoint.x, lastPoint.y - firstPoint.y));
                break;
            case -1:
            case 2:
                firstPoint.x += Costanti.SHIP_WIDTH;
                firstPoint.y += Costanti.SHIP_HEIGHT;

                r = new Rectangle(lastPoint, new Dimension(firstPoint.x - lastPoint.x, firstPoint.y - lastPoint.y));
                break;
        }

        return r;
    }
}
