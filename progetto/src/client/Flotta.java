package client;

import java.util.List;
import java.util.ArrayList;

public class Flotta {
    public List<Coordinata[]> flotta = new ArrayList<>(){
                                                            {Coordinata[] nave1 = new Coordinata[2];}
                                                            {Coordinata[] nave2 = new Coordinata[2];}
                                                            {Coordinata[] nave3 = new Coordinata[3];}
                                                            {Coordinata[] nave4 = new Coordinata[4];}
                                                            {Coordinata[] nave5 = new Coordinata[5];}
                                                        };

    public void setPosizione(List<Coordinata> posizioni) {
        for (Coordinata[] nave : flotta) {
            if (nave.length == posizioni.size() && nave[0].X == -1) {
                for (int i = 0; i < posizioni.size(); i++) {
                    nave[i].X = posizioni.get(i).X;
                    nave[i].Y = posizioni.get(i).Y;
                }
            }
        }
    }
}
