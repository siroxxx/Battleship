package client;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

// classe della minimappa
public class Minimappa {
    List<List<Integer>> minimappa = new ArrayList<>();// la minimappa è una lista di liste di interi

    // riempe la minimappa di tutti 0
    public Minimappa() {
        for (int i = 0; i < Costanti.COLONNE; i++) {
            minimappa.add(new ArrayList<>());
            for (int k = 0; k < Costanti.RIGHE; k++) {
                minimappa.get(i).add(0);
            }
        }
    }

    // vengono aggiunte le navi prese da un oggetto 'Flotta' alla minimappa
    public void setNavi(Flotta f) {
        for (Nave n : f.flotta) {
            for (Point p : n.coordinate) {
                Point point = getMapPoint(p, Costanti.WIDTH);

                minimappa.get(point.y).set(point.x, n.id);
            }
        }

        String str = "";
        for (int i = 0; i < Costanti.COLONNE; i++) {
            for (int k = 0; k < Costanti.RIGHE; k++) {
                str += minimappa.get(i).get(k);
            }
            str += "\n";
        }

        System.out.println(str);
    }

    // trova il a che coordinate della minimappa corrisponde un click sulla mappa
    // normale
    public static Point getMapPoint(Point p, int gap) {
        Point point = new Point();

        point.x = (int) ((p.x - Costanti.MAP_X - gap) / Costanti.WIDTH);
        point.y = (int) ((p.y - Costanti.MAP_Y) / Costanti.HEIGHT);

        return point;
    }
}
