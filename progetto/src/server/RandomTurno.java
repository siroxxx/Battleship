package server;

import java.util.Random;


public class RandomTurno {
    Condivisa c;
    Random r;
    public RandomTurno(Condivisa cond){
        c=cond;
        r=new Random();
    }
    public void impostaTurni(){     //randomizzo i turni iniziali: se 0 g1 attacca, se 1 g2 attacca
        Integer rand=r.nextInt(0,2);
        if(rand==0){    //g1 attacca g2 è in difesa
            c.turno=true;
        }
        else {      //g2 attacca g1 è in difesa
            c.turno=false;
        }
    }
}
