package client;

import java.awt.Dimension;
import java.awt.Toolkit;

public class condivisa {
    public int stato;
    public int WIDTH;
    public int HEIGHT;
    public int MAP_WIDTH;
    public int MAP_HEIGHT;

    public condivisa() {
        //WIDTH : 100 = SIZE : 1920
        //WIDTH = 100 * SIZE / 1920

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = 100 * screenSize.width / 1920;
        HEIGHT = 100 * screenSize.height / 1080;

        MAP_WIDTH = WIDTH * Costanti.colonne;
        MAP_HEIGHT = HEIGHT * Costanti.righe;
    }
}
