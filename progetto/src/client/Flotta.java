package client;

import java.util.List;
import java.util.ArrayList;

public class Flotta {
    public List<Nave> flotta = new ArrayList<>();

    public Flotta() {
        for (int i = 0; i < 5; i++) {
            flotta.add(new Nave((i == 0 ? 2 : i + 1), i + 1));
        }
    }
}
