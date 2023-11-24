package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

//classe contenente tutte le costanti del progetto lato client
public class Costanti {
    public static final String INDIRIZZO = "127.0.0.1";// indirizzo ip del server
    public static final int PORTA_SERVER = 777;// porta del server

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// dimensioni dello schermo

    // valori per la mappa
    public static final int COLONNE = 10;// colonne della griglia
    public static final int RIGHE = 10;// righe della griglia
    public static final int WIDTH = 100 * screenSize.width / 1920; // larghezza di un singolo blocco
    public static final int HEIGHT = 100 * screenSize.height / 1080; // altezza di un singolo blocco
    public static final int MAP_WIDTH = WIDTH * COLONNE;// larghezza
    public static final int MAP_HEIGHT = HEIGHT * RIGHE;// altezza
    public static final int MAP_X = ((screenSize.width - MAP_WIDTH) / 2);// cordinata x del punto in alto a sinistra
    public static final int MAP_Y = ((screenSize.height - MAP_HEIGHT) / 2);// cordinata y del punto in alto a sinistra
    public static final int SHIP_WIDTH = WIDTH;// larghezza di un blocco della nave
    public static final int SHIP_HEIGHT = HEIGHT;// altezza di un blocco della nave
    public static final Rectangle MAP = new Rectangle(MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);// oggetto rettangolo
    public static final Color MAP_CYAN = new Color(30, 129, 176);// colore di sfondo

    public static final Font FONT = new Font("Congenial Black", Font.BOLD, 30 * screenSize.width / 1920);// font delle
                                                                                                         // scritte

    // valori per la minimappa
    public static final int MINIMAP_BLOCK_WIDTH = WIDTH / 3; // larghezza di un singolo blocco
    public static final int MINIMAP_BLOCK_HEIGHT = HEIGHT / 3; // altezza di un singolo blocco
    public static final int MINIMAP_WIDTH = MINIMAP_BLOCK_WIDTH * COLONNE;// larghezza
    public static final int MINIMAP_HEIGHT = MINIMAP_BLOCK_HEIGHT * RIGHE;// altezza
    public static final int MINIMAP_X = ((screenSize.width - MAP_WIDTH) / 2) + MAP_WIDTH// cordinata x del punto in alto
                                                                                        // a sinistra
            + ((((screenSize.width - MAP_WIDTH) / 2) - MINIMAP_WIDTH) / 2);
    public static final int MINIMAP_Y = MAP_Y + HEIGHT;// cordinata x del punto in alto
                                                       // a sinistra

    // costanti per il movimento della nave senza l'utilizzo dei bottoni
    static final int LEFT = 37;
    static final int RIGHT = 39;
    static final int ENTER = 10;
}
