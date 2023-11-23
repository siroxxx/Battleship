package client;

import java.util.List;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

// classe della flotta
public class Flotta {
    public List<Nave> flotta = new ArrayList<>(); // la flotta è una lista di oggetti 'Nave'

    public Flotta() {
        for (int i = 0; i < 5; i++) {
            flotta.add(new Nave((i == 0 ? 2 : i + 1), i + 1));
        }
    }

    // ritorna il rettangolo corrispondente ad una data nave
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
