package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

// classe dove viene gestita la fase di pogettazione della partita
public class FasePreparazione extends MyPanel {
    private Point initialPoint = null;// punto inziale di dove si è iniziato a trascinare la nave
    private Nave initialNave = null;// copia della nave di prima che essa venga trascinata
    private static int mapGap = Costanti.WIDTH;// gap da lasciare a sinistra della mappa per farci stare le navi
    private int numRotazione;// numero della nave che può essere rotata
    private JButton btnRotazioneSx, btnRotazioneDx;// pulsanti per la rotazione
    public static Point gap = new Point(Costanti.MAP_X + mapGap, Costanti.MAP_Y);// spazio tra la coordinata della mappa
                                                                                 // e l'inizio del pannello

    public FasePreparazione() throws IOException {
        // BufferedImage buttonIcon = ImageIO.read(new File("\\frecciaSx.jpg")); non
        // trova le immagini

        btnRotazioneSx = creaButton("sinistra", true);
        btnRotazioneDx = creaButton("destra", false);

        this.add(btnRotazioneDx);
        this.add(btnRotazioneSx);

        SwingUtilities.updateComponentTreeUI(this);
    }

    // metodo per la creazione dei pulsanti
    private JButton creaButton(String nome, boolean isSx) {
        JButton button = new JButton(nome);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nave nave = condivisa.listaNavi.flotta.get(numRotazione - 1);
                nave.ruotaNave((isSx ? -1 : 1)); // -1 a Sx; 1 a Dx
                setPosizione(nave);
                repaint();
            }
        });

        button.setBounds(200, 100, 200, 200); // TODO: da sistemare coordinate bottoni

        return button;
    }

    // metodo per disegnare la mappa, le navi e le stringhe
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, mapGap, condivisa.mappaDifesa);

        Font f = Costanti.FONT;
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("INSERISCI LE TUE NAVI:", Costanti.WIDTH / 2, Costanti.HEIGHT);

        disegnaNavi(g2);
    }

    // controllo e spostamento delle navi quando trascinate col mouse
    @Override
    public void mouseDragged(MouseEvent e) {
        if (cond.nave != -1) {
            if (initialPoint == null)
                initialPoint = e.getPoint();

            boolean isFirstTouch = false;
            for (int i = 0; i < condivisa.listaNavi.flotta.get(cond.nave - 1).coordinate.size(); i++) {
                Point coord = condivisa.listaNavi.flotta.get(cond.nave - 1).coordinate.get(i);

                if (initialNave == null) {
                    isFirstTouch = true;
                    initialNave = new Nave(condivisa.listaNavi.flotta.get(cond.nave - 1));
                    numRotazione = initialNave.id;
                }

                if (isFirstTouch) {
                    initialNave.coordinate.get(i).setLocation(coord.getX(), coord.getY());
                }

                coord.x = e.getX() - (int) (initialPoint.getX() - initialNave.coordinate.get(i).getX());
                coord.y = e.getY() - (int) (initialPoint.getY() - initialNave.coordinate.get(i).getY());
            }

            repaint();
        }
    }

    // metodo per quando il tasto del mouse viene rilasciato
    @Override
    public void mouseReleased(MouseEvent e) {
        if (initialPoint != null && initialNave != null) {
            setPosizione(condivisa.listaNavi.flotta.get(initialNave.id - 1));

            initialPoint = null;
            initialNave = null;
        }
        if (cond.nave != -1)
            cond.nave = -1;

        // codice finché non va il keyListener
        // tasto destro va alla fase di attacco
        if (e.getButton() == 3) {
            Boolean tuttoSettato = true;

            for (Nave n : condivisa.listaNavi.flotta)
                if (!n.isSet)
                    tuttoSettato = false;

            if (tuttoSettato) {
                condivisa.setMinimappa();
                condivisa.stato = -1;
            }
        }

        if (numRotazione != 0) {
            Nave nave = condivisa.listaNavi.flotta.get(numRotazione - 1);
            if (e.getButton() == 4) {// gira a sx
                nave.ruotaNave((-1));
                setPosizione(nave);
            }
            if (e.getButton() == 5) {// gira a dx
                nave.ruotaNave((1));
                setPosizione(nave);
            }
        }
        repaint();
    }

    // setta la poszione della nave nella mappa quando si rilascia al suo interno
    public void setPosizione(Nave nave) {
        Nave naveCopia = new Nave(nave);
        Rectangle mapRect = new Rectangle(Costanti.MAP);
        mapRect.x += mapGap;

        Point quadranteCoord = new Point();

        quadranteCoord.x = ((int) ((naveCopia.coordinate.get(0).x - gap.x) / Costanti.WIDTH)) * Costanti.WIDTH
                + gap.x;
        quadranteCoord.y = ((int) ((naveCopia.coordinate.get(0).y - gap.y) / Costanti.HEIGHT)) * Costanti.HEIGHT
                + gap.y;

        Point newCoord = new Point();
        for (int i = 0; i < nave.coordinate.size(); i++) {
            Point p = nave.coordinate.get(i);

            if (i == 0)
                newCoord = new Point(nave.coordinate.get(i + 1).x - p.x, nave.coordinate.get(i + 1).y - p.y);

            p.x = quadranteCoord.x;
            p.y = quadranteCoord.y;

            quadranteCoord.x += newCoord.x;
            quadranteCoord.y += newCoord.y;
        }

        if (!checkOverlaps(Flotta.getRectangle(nave), nave.id))
            nave.resettaNave();
        // controlla che la nave sia stata posizionata all'interno della mappa
        else if (!mapRect.contains(Flotta.getRectangle(nave)))
            nave.resettaNave();
        else
            nave.isSet = true;

        repaint();
    }

    // controlla che la nave non sia stata posizionata su un'altra nave
    public boolean checkOverlaps(Rectangle r, int num) {
        for (Nave n : condivisa.listaNavi.flotta)
            if (n.id != num) {
                Rectangle rect = Flotta.getRectangle(n);
                if (rect.x < r.x + r.width && rect.x + rect.width > r.x && rect.y < r.y + r.height
                        && rect.y + rect.height > r.y)
                    return false;
            }

        return true;
    }

    // non funziona
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);

        switch (e.getKeyCode()) {
            case Costanti.LEFT:
                condivisa.listaNavi.flotta.get(numRotazione - 1).ruotaNave(-1);
                break;

            case Costanti.RIGHT:
                condivisa.listaNavi.flotta.get(numRotazione - 1).ruotaNave(1);
                break;
            case Costanti.ENTER:
                condivisa.stato = -1;
                break;
        }
    }
}
