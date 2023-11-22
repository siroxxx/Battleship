package client;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Nave {
    List<Point> coordinate = new ArrayList<Point>();
    int lunghezza;
    boolean isSet = false;
    int rotazione; // 1: verso destra; -2: verso il basso; -1: verso sinistra; 2: verso l'alto
    int numero;

    public Nave(int lunghezza, int n) {
        this.lunghezza = lunghezza;
        numero = n;
        rotazione = 1;

        for (int i = 0; i < lunghezza; i++)
            coordinate.add(new Point());

        resettaNave();
    }

    public Nave(Nave nave) {
        coordinate = new ArrayList<>();

        for (Point p : nave.coordinate)
            coordinate.add(new Point(p));

        lunghezza = nave.lunghezza;
        isSet = nave.isSet;
        rotazione = nave.rotazione;
        numero = nave.numero;
    }

    public void resettaNave() {
        for (int i = 0; i < this.lunghezza; i++) {
            coordinate.set(i, new Point(Costanti.WIDTH / 2 + Costanti.WIDTH * i,
                    Costanti.HEIGHT * 2 + Costanti.HEIGHT * numero + numero * 10));
        }
        isSet = false;
        rotazione = 1;
    }

    // left: -1; right: 1
    public void ruotaNave(int direzione) {
        List<Point> copiaCoord = new ArrayList<>();

        for (Point p : coordinate)
            copiaCoord.add(new Point(p));

        switch (rotazione) {
            case 1:
            case -1:
                for (int i = 0; i < lunghezza; i++) {
                    coordinate.get(i).x = copiaCoord.get(0).x;
                    coordinate.get(i).y = copiaCoord.get(i).y + (Costanti.WIDTH * rotazione * direzione * i);
                }

                if (copiaCoord.get(0).x > copiaCoord.get(1).x) {
                    if (direzione == -1)
                        rotazione = -2;
                    else
                        rotazione = 2;
                } else {
                    if (direzione == -1)
                        rotazione = 2;
                    else
                        rotazione = -2;
                }

                break;

            case 2:
            case -2:
                for (int i = 0; i < lunghezza; i++) {
                    coordinate.get(i).y = copiaCoord.get(0).y;
                    coordinate.get(i).x = copiaCoord.get(i).x + (Costanti.WIDTH * (rotazione / 2) * direzione * i);
                }

                if (copiaCoord.get(0).y > copiaCoord.get(1).y) {
                    if (direzione == -1)
                        rotazione = -1;
                    else
                        rotazione = 1;
                } else {
                    if (direzione == -1)
                        rotazione = 1;
                    else
                        rotazione = -1;
                }

                break;
        }
    }
}
