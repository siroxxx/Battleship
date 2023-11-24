package client;

// thread che attende che 'Condivisa.stato' diventi -1 e continua l'esecuzione di 'MyFrame.java'
public class AspettaStato extends Thread {
    public AspettaStato() {
    }

    @Override
    public void run() {
        while (condivisa.stato == 0) {

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MyFrame.fasePrep.add(new FaseAttDif());
        interrupt();
    }
}
