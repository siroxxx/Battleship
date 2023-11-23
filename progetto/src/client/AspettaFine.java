package client;

// thread che attende che 'Condivisa.stato' diventi -1 e continua l'esecuzione di 'MyFrame.java'
public class AspettaFine extends Thread {
    public AspettaFine() {

    }

    @Override
    public void run() {
        while (Condivisa.stato != -1) {

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
