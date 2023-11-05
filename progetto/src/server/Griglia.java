package server;
/**
 * griglia
 */
public class Griglia {                  //STATO 0: acqua non colpita | STATO 1: acqua colpita | STATO 2: nave non colpita | STATO 3: nave colpita

    Integer [][] campo;

    public Griglia(){
        for (int i = 0; i < costanti.righe; i++) {
            for (int j = 0; j < costanti.colonne; j++) {
                campo[i][j]=0;
            }
        }
    }
}