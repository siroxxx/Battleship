package client;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class condivisa {
    public int stato;
    public Flotta listaNavi = new Flotta();
    public int nave = -1;

    public condivisa() {

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
            case 0:
            case 1:
                lastPoint.x += Costanti.WIDTH / 4 * 3;
                lastPoint.y += Costanti.HEIGHT / 4 * 3;

                r = new Rectangle(firstPoint, new Dimension(lastPoint.x - firstPoint.x, lastPoint.y - firstPoint.y));
                break;
            case 2:
            case 3:
                firstPoint.x += Costanti.WIDTH / 4 * 3;
                firstPoint.y += Costanti.HEIGHT / 4 * 3;

                r = new Rectangle(lastPoint, new Dimension(firstPoint.x - lastPoint.x, firstPoint.y - lastPoint.y));
                break;
        }

        return r;
    }
}
