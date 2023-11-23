package client;

import java.util.List;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

// classe della nave
public class Nave {
    List<Point> coordinate = new ArrayList<Point>(); // le coordinate sono una lista di oggetti 'Point'
    int lunghezza; // indica la lunghezza della lista
    boolean isSet = false; // indica se la nave è stat poszionata sulla mappa
    int rotazione; // 1: verso destra; -2: verso il basso; -1: verso sinistra; 2: verso l'alto
    int id; // è l'id della nave
    Color colore; // il colore della nave

    public Nave(int lunghezza, int n) {
        this.lunghezza = lunghezza;
        id = n;
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
        id = nave.id;
        colore = nave.colore;
    }

    // le coordinate della nave vengono settate alla loro posizione di base
    public void resettaNave() {
        for (int i = 0; i < this.lunghezza; i++) {
            coordinate.set(i, new Point(Costanti.WIDTH / 2 + Costanti.WIDTH * i,
                    Costanti.HEIGHT * 2 + Costanti.HEIGHT * id + id * 10));
        }
        isSet = false;
        rotazione = 1;
    }

    // ruota la nave a sinistra o a destra
    // direzione: sinistra = -1; destra = 1
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
