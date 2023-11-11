package server;
/**
 * griglia
 */
public class Griglia {                  //STATO 0: acqua non colpita | STATO 1: acqua colpita | STATO 2: nave non colpita | STATO 3: nave colpita

    public Integer [][] campo;
    public Integer [] nave1=new Integer[2];            //andranno passate in ordine le navi: 2/2/3/4/5
    public Integer [] nave2=new Integer[2];            //andranno passate in ordine le navi: 2/2/3/4/5
    public Integer [] nave3=new Integer[3];            //andranno passate in ordine le navi: 2/2/3/4/5
    public Integer [] nave4=new Integer[4];            //andranno passate in ordine le navi: 2/2/3/4/5
    public Integer [] nave5=new Integer[5];            //andranno passate in ordine le navi: 2/2/3/4/5
    public Griglia(){
        for (int i = 0; i < costanti.righe; i++) {
            for (int j = 0; j < costanti.colonne; j++) {
                campo[i][j]=0;
            }
        }
    }
    public void crea(Integer[] messaggio)
    {
        for (int i = 0; i < messaggio.length; i++) {
            
        }
    }
}