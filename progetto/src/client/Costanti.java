package client;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Costanti {
    public static final String INDIRIZZO="127.0.0.1";
    public static final Integer PORTA_SERVER=777;
    public static final Integer COLONNE=10;
    public static final Integer RIGHE=10;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = 100 * screenSize.width / 1920;         //WIDTH : 100 = SIZE : 1920
    public static final int HEIGHT = 100 * screenSize.height / 1080;       //WIDTH = 100 * SIZE / 1920
    public static final int MAP_WIDTH = WIDTH * Costanti.COLONNE;
    public static final int MAP_HEIGHT = HEIGHT * Costanti.RIGHE;
    public static final int FONT = 30 * screenSize.width / 1920;
}
