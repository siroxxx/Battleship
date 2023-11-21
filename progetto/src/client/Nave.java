package client;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Nave {
    List<Point> coordinate = new ArrayList<Point>();
    int lunghezza;
    boolean isSet = false;
    int rotazione; //1: verso destra; 2: verso il basso; -1: verso sinistra; 2: verso l'alto
    int numero;

    public Nave(int lunghezza, int n) {
        this.lunghezza = lunghezza;
        numero = n;
        
        resettaNave(n);
    }

    public void resettaNave(int nave) {
        for (int i = 0; i < this.lunghezza; i++) {
            coordinate.add(new Point(Costanti.WIDTH / 2 + Costanti.WIDTH * i, Costanti.HEIGHT * 2 + Costanti.HEIGHT * nave));
        }
    }

    public void ruotaNave(int direzione) {
        List<Point> copiaCoord = new ArrayList<>(coordinate);

        switch (rotazione) {
            case 1:
            case -1:
                for (int i = 0; i < lunghezza; i++) {
                    coordinate.get(i).x = copiaCoord.get(0).x;
                    coordinate.get(i).y = copiaCoord.get(i).y + (Costanti.WIDTH * rotazione * direzione * i);
                }

                break;

            case 2:
                for (int i = 0; i < lunghezza; i++) {
                    coordinate.get(i).y = copiaCoord.get(0).y;
                    coordinate.get(i).x = copiaCoord.get(i).x + (Costanti.WIDTH * (rotazione/2) * direzione * i);
                }

                break;
        }
    }
}
