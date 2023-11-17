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
    public Integer[][] campo=new Integer[Costanti.righe][Costanti.colonne]; 
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
    public Boolean [] statoNavi=new Boolean[5]; //vettore per capire se le navi sono affondate o meno

    public Griglia() {
        for (int i = 0; i < Costanti.righe; i++) {  //di base metto gli sapzi come acqua non colpita
            for (int j = 0; j < Costanti.colonne; j++) {
                campo[i][j] = 0;
            }
        }
        for (int i = 0; i < statoNavi.length; i++) {
            statoNavi[i]=false;
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
        nave1x = nave1xtemp.toArray(new Integer[nave1x.length]);
        nave1y = nave1ytemp.toArray(new Integer[nave1x.length]);
        nave2x = nave2xtemp.toArray(new Integer[nave1x.length]);
        nave2y = nave2ytemp.toArray(new Integer[nave1x.length]);
        nave3x = nave3xtemp.toArray(new Integer[nave1x.length]);
        nave3y = nave3ytemp.toArray(new Integer[nave1x.length]);
        nave4x = nave4xtemp.toArray(new Integer[nave1x.length]);
        nave4y = nave4ytemp.toArray(new Integer[nave1x.length]);
        nave5x = nave5xtemp.toArray(new Integer[nave1x.length]);
        nave5y = nave5ytemp.toArray(new Integer[nave1x.length]);

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
    public Boolean[] aggiornaGriglia(Integer[]x, Integer[] y){      //metodo generale per l'aggiornamento della griglia 

        Boolean [] risultatiColpi =new Boolean[x.length];   //vettore in cui salvo il/i risultato/i del/dei colpo/i
        for (int i = 0; i < x.length; i++) {
            if (campo[x[i]][y[i]]==2) {     //2: stato di nave non colpita
                campo[x[i]][y[i]]=3;       //passaggio allo stato di nave colpita
                risultatiColpi[i]=true;     //colpito
            }
            else if(campo[x[i]][y[i]]==0){  //0:stato di acqua non colpita
                campo[x[i]][y[i]]=1;       //passaggio allo stato di acqua colpita
                risultatiColpi[i]=false;    //non colpito
            }
            else {      //generalmente per caselle già colpite
                risultatiColpi[i]=false;    //non colpito
            }
        }
        return risultatiColpi;
    }
    public Boolean[] spara(Integer[] dati){     //metodo generale che gestisce lo sparo di colpi singoli, bombe e poteri speciali
        List <Integer> x =new ArrayList<Integer>();
        List <Integer> y =new ArrayList<Integer>();
        for (int i = 0; i < x.size(); i++) {
            if(i%2==0){
                x.add(dati[i]);
            }
            else y.add(dati[i]);
        }

        return aggiornaGriglia((Integer[])x.toArray(), (Integer[])y.toArray());
    }
    public List<String> controllaNavi(){
        List<String> naviAffondate=new ArrayList<String>();   //lista per le navi affondate, se un campo vale 10 vuol dire che tutte le navi sono state affondate
        if(statoNavi[0]==false){     //se non ancora affondata
            Boolean affondata=true;
            for (int i = 0; i < nave1x.length; i++) {
                if(campo[nave1x[i]][nave1y[i]]!=3){
                    affondata=false;
                }
            }
            if(affondata==true){
                naviAffondate.add("1");
                statoNavi[0]=true;
            }
                
        }
        if(statoNavi[1]==false){
            Boolean affondata=true;
            for (int i = 0; i < nave1x.length; i++) {
                if(campo[nave2x[i]][nave2y[i]]!=3){
                    affondata=false;
                }
            }
            if(affondata==true){
                naviAffondate.add("2");
                statoNavi[1]=true;
            }
        }
        if(statoNavi[2]==false){
            Boolean affondata=true;
            for (int i = 0; i < nave1x.length; i++) {
                if(campo[nave3x[i]][nave3y[i]]!=3){
                    affondata=false;
                }
            }
            if(affondata==true){
                naviAffondate.add("3");
                statoNavi[2]=true;
            }
        }
        if(statoNavi[3]==false){
            Boolean affondata=true;
            for (int i = 0; i < nave1x.length; i++) {
                if(campo[nave4x[i]][nave4y[i]]!=3){
                    affondata=false;
                }
            }
            if(affondata==true){
                naviAffondate.add("4");
                statoNavi[3]=true;
            }
        }
        if(statoNavi[4]==false){
            Boolean affondata=true;
            for (int i = 0; i < nave1x.length; i++) {
                if(campo[nave5x[i]][nave5y[i]]!=3){
                    affondata=false;
                }
            }
            if(affondata==true){
                naviAffondate.add("5");
                statoNavi[4]=true;
            }
        }
        Boolean tutteAffondate=true;
        for (int i = 0; i < statoNavi.length; i++) {
            if (statoNavi[i]==false) {
                tutteAffondate=false;
            }
        }
        if(tutteAffondate==true)
            naviAffondate.add("all");      //10:codice di fine partita
        return naviAffondate;
    }
    public Integer radar(Integer x, Integer y){     //gestione radar
        Integer conteggio=0;
        for (int i = x-1; i < x+2; i++) {       //questi due for sono per considerare il quadrato 3x3 attorno al 
            for (int j = y-1; j < y+2; j++) {
                if(campo[i][j]==2){
                    conteggio+=1;
                }
            }
        }
        return conteggio;
    }
}