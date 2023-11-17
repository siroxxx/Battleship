package client;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;

public class Flotta {
    public List<Nave> flotta = new ArrayList<>();

    public Flotta() {
        for (int i = 0; i < 5; i++) {
            flotta.add(new Nave((i == 0 ? 2 : i + 1), i + 1));
        }
    }

    public void setPosizione(List<Point> posizioni) {
        for (Nave nave : flotta)
            if (nave.lunghezza == posizioni.size() && !nave.isSet)
                for (int i = 0; i < posizioni.size(); i++)
                    nave.coordinate.set(i, posizioni.get(i));
    }
}
