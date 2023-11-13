package server;

import java.util.ArrayList;
import java.util.List;

/**
 * griglia
 */
public class Griglia {
    // STATO 0: acqua non colpita | STATO 1: acqua colpita
    // STATO 2: nave non colpita | STATO 3: nave colpita
    //salvo le x di una nave in un vettore e le y di una nave in un altro
    public Integer[][] campo; 
    public Integer[] nave1x = new Integer[2]; // andranno passate in ordine le navi: 2/2/3/4/5
    public Integer[] nave1y = new Integer[2];
    public Integer[] nave2x = new Integer[2];
    public Integer[] nave2y = new Integer[2];
    public Integer[] nave3x = new Integer[3];
    public Integer[] nave3y = new Integer[3];
    public Integer[] nave4x = new Integer[4];
    public Integer[] nave4y = new Integer[4];
    public Integer[] nave5x = new Integer[5];
    public Integer[] nave5y = new Integer[5];

    public Griglia() {
        for (int i = 0; i < Costanti.righe; i++) {  //di base metto gli sapzi come acqua non colpita
            for (int j = 0; j < Costanti.colonne; j++) {
                campo[i][j] = 0;
            }
        }
    }

    public void crea(Integer[] messaggio)   //mi faccio passare le navi in ordine 
    {
        creanavi(messaggio);
        creacampo();
    }

   

    public void creanavi(Integer[] messaggio){
        List<Integer> nave1xtemp = new ArrayList<Integer>(); //creo delle navi temporanee in formato lista perchè più comodo
        List<Integer> nave1ytemp = new ArrayList<Integer>(); 
        List<Integer> nave2xtemp = new ArrayList<Integer>(); 
        List<Integer> nave2ytemp = new ArrayList<Integer>(); 
        List<Integer> nave3xtemp = new ArrayList<Integer>(); 
        List<Integer> nave3ytemp = new ArrayList<Integer>(); 
        List<Integer> nave4xtemp = new ArrayList<Integer>(); 
        List<Integer> nave4ytemp = new ArrayList<Integer>(); 
        List<Integer> nave5xtemp = new ArrayList<Integer>(); 
        List<Integer> nave5ytemp = new ArrayList<Integer>(); 
        for (int i = 0; i < messaggio.length; i++) { //in questo for salvo le coordinate nei vettori delle navi decrementando la i
            if(i%2==0){ //se sono delle X
                if(i<4)
                nave1xtemp.add(messaggio[i]);
            else if(i<8)
                nave2xtemp.add(messaggio[i]);
            else if(i<14)
                nave3xtemp.add(messaggio[i]);
            else if(i<22)
                nave4xtemp.add(messaggio[i]);
            else nave5xtemp.add(messaggio[i]);
            }
            else{   //se sono delle Y
                if(i<4)
                nave1ytemp.add(messaggio[i]);
            else if(i<8)
                nave2ytemp.add(messaggio[i]);
            else if(i<14)
                nave3ytemp.add(messaggio[i]);
            else if(i<22)
                nave4ytemp.add(messaggio[i]);
            else nave5ytemp.add(messaggio[i]);
            }
        }
        //trasformo le liste in array
        nave1x = (Integer[]) nave1xtemp.toArray();
        nave1y = (Integer[]) nave1xtemp.toArray();
        nave2x = (Integer[]) nave1xtemp.toArray();
        nave2y = (Integer[]) nave1xtemp.toArray();
        nave3x = (Integer[]) nave1xtemp.toArray();
        nave3y = (Integer[]) nave1xtemp.toArray();
        nave4x = (Integer[]) nave1xtemp.toArray();
        nave4y = (Integer[]) nave1xtemp.toArray();
        nave5x = (Integer[]) nave1xtemp.toArray();
        nave5y = (Integer[]) nave1xtemp.toArray();
    }
    public void creacampo() {   //per ogni nave metto lo stato corrispondente della casella nella griglia-> stato 2: nave presente non colpita

        for (int i = 0; i < nave1x.length; i++) {
            campo[nave1x[i]][nave1y[i]]=2;
        }
        for (int i = 0; i < nave2x.length; i++) {
            campo[nave2x[i]][nave2y[i]]=2;
        }
        for (int i = 0; i < nave3x.length; i++) {
            campo[nave3x[i]][nave3y[i]]=2;
        }
        for (int i = 0; i < nave4x.length; i++) {
            campo[nave4x[i]][nave4y[i]]=2;
        }
        for (int i = 0; i < nave5x.length; i++) {
            campo[nave5x[i]][nave5y[i]]=2;
        }
    }
}