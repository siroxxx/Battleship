package server;

/**
 * griglia
 */
public class Griglia { // STATO 0: acqua non colpita | STATO 1: acqua colpita | STATO 2: nave non
                       // colpita | STATO 3: nave colpita

    public Integer[][] campo; // i vettori hanno dimensioni doppie perchè tengo x e y separate
    public Integer[] nave1 = new Integer[4]; // andranno passate in ordine le navi: 2/2/3/4/5
    public Integer[] nave2 = new Integer[4]; // andranno passate in ordine le navi: 2/2/3/4/5
    public Integer[] nave3 = new Integer[6]; // andranno passate in ordine le navi: 2/2/3/4/5
    public Integer[] nave4 = new Integer[8]; // andranno passate in ordine le navi: 2/2/3/4/5
    public Integer[] nave5 = new Integer[10]; // andranno passate in ordine le navi: 2/2/3/4/5

    public Griglia() {
        for (int i = 0; i < Costanti.righe; i++) {
            for (int j = 0; j < Costanti.colonne; j++) {
                campo[i][j] = 0;
            }
        }
    }

    public void crea(Integer[] messaggio)   //mi faccio passare le navi in ordine 
    {
        //TODO:salvare le coordinate in maniera migliore e creare il campo
        for (int i = 0; i < messaggio.length; i++) { //in questo for salvo le coordinate nei vettori delle navi decrementando la i
            if(i<4)
                nave1[i]=messaggio[i];
            else if(i<8)
                nave2[i-4]=messaggio[i];
            else if(i<14)
                nave3[i-8]=messaggio[i];
            else if(i<22)
                nave4[i-14]=messaggio[i];
            else nave5[i-22]=messaggio[i];
        }

    }
}