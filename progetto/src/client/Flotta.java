package client;

import java.util.List;
import java.util.ArrayList;

public class Flotta {
    public List<Nave> flotta = new ArrayList<>(){
                                                    {Nave nave1 = new Nave(2, 1);}
                                                    {Nave nave2 = new Nave(2, 2);}
                                                    {Nave nave3 = new Nave(3, 3);}
                                                    {Nave nave4 = new Nave(4, 4);}
                                                    {Nave nave5 = new Nave(5, 5);}
                                                };

    public Flotta() {
        
    }

    public void setPosizione(List<Coordinata> posizioni) {
        for (Nave nave : flotta)
            if (nave.lunghezza == posizioni.size() && !nave.isSet)
                for (int i = 0; i < posizioni.size(); i++)
                    nave.coordinate.set(i, posizioni.get(i));
    }
}
