package client;

public class AspettaFine extends Thread {
    public AspettaFine() {

    }

    @Override
    public void run() {
        while (Condivisa.stato != -1) {
            // Il thread è in attesa che Condivisa.stato diventi -1
        }
        // Quando Condivisa.stato diventa -1, interrompe il thread
        MyFrame.fase1.add(new FaseAttacco());
        interrupt();
    }
}
