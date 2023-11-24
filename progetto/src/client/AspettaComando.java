package client;

// thread che attende che 'Condivisa.stato' diventi -1 e continua l'esecuzione di 'MyFrame.java'
public class AspettaComando extends Thread {
    public AspettaComando() {
    }

    @Override
    public void run() {
        while (FaseAttDif.comando == null && condivisa.stato == 1) {

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        interrupt();
    }
}
