package client;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Nave {
    List<Point> coordinate = new ArrayList<Point>();
    int lunghezza;
    boolean isSet = false;
    int rotazione; //0: verso destra; 1: verso il basso; 2: verso sinistra; 3: verso l'alto
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

    //fare ruota nave
}
