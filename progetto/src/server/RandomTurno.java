package server;

import java.util.Random;

public class RandomTurno {
    Condivisa c1;
    Condivisa c2;
    Random r;
    public RandomTurno(Condivisa cond1, Condivisa cond2){
        c1=cond1;
        c2=cond2;
        r=new Random();
    }
    public void impostaTurni(){     //randomizzo i turni iniziali: se 0 g1 attacca, se 1 g2 attacca
        Integer rand=r.nextInt(0,2);
        if(rand==0){    //g1 attacca g2 è in difesa
            c1.turno=true;
            c2.turno=false;
        }
        else {      //g2 attacca g1 è in difesa
            c1.turno=false;
            c2.turno=true;
        }
    }
}
