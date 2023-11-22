package client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Costanti {
    public static final String INDIRIZZO = "127.0.0.1";
    public static final Integer PORTA_SERVER = 777;
    public static final Integer COLONNE = 10;
    public static final Integer RIGHE = 10;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = 100 * screenSize.width / 1920; // WIDTH : 100 = SIZE : 1920
    public static final int HEIGHT = 100 * screenSize.height / 1080; // WIDTH = 100 * SIZE / 1920
    public static final int MAP_WIDTH = WIDTH * COLONNE;
    public static final int MAP_HEIGHT = HEIGHT * RIGHE;
    public static final int MAP_X = ((screenSize.width - MAP_WIDTH) / 2);
    public static final int MAP_Y = ((screenSize.height - MAP_HEIGHT) / 2);
    public static final int FONT = 30 * screenSize.width / 1920;
    public static final int SHIP_WIDTH = WIDTH;
    public static final int SHIP_HEIGHT = HEIGHT;
    public static final Rectangle MAP = new Rectangle(MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);

    // costanti per il movimento della nave senza l'utilizzo dei bottoni
    static final int LEFT = 37;
    static final int RIGHT = 39;
    static final int ENTER = 10;
}
