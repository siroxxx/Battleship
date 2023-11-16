package client;

import java.util.List;
import java.util.ArrayList;

public class Nave {
    List<Coordinata> coordinate = new ArrayList<Coordinata>();
    int lunghezza;
    boolean isSet = false;

    public Nave(int lunghezza, int n) {
        this.lunghezza = lunghezza;
        condivisa cond = new condivisa();

        for (int i = 0; i < this.lunghezza; i++) {
            coordinate.add(new Coordinata(cond.WIDTH / 2 + cond.WIDTH * i, cond.HEIGHT * 2 + cond.HEIGHT * n));
        }
    }
}
