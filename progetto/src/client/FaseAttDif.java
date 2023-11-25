package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

// classe dove viene gestita la fase di attacco e difesa della partita
public class FaseAttDif extends MyPanel {
    public static Minimappa mappa = new Minimappa(); // 0: cella ignota; -1 cella vuota; 1: cella colpita; 2: nave
                                                     // affondata
    public int energia = 0; // energia disponibile per le abilità speciali
    private JButton btnBomba, btnAereo, btnRadar; // pulsanti per le abilità speciali
    private Boolean isBombing = false, isAirAttack = false; // true se viene premuto il pulsante per la bomba con
                                                            // abbastanza energia
    private Boolean isRadar = false; // true se viene premuto il pulsante per il radar con abbastanza energia
    private List<Point> listCoordRadar = new ArrayList<>();
    private List<Integer> listNumRadar = new ArrayList<>();
    private Boolean isVittoria = null; // false: hai perso; true: hai vinto
    public List<Point> listSpari = new ArrayList<>();
    public static String comando = null;

    public FaseAttDif() {
        btnBomba = creaButton("BOMBA");
        btnAereo = creaButton("ATTACCO AEREO");
        btnRadar = creaButton("RADAR");

        this.add(btnBomba);
        this.add(btnAereo);
        this.add(btnRadar);

        SwingUtilities.updateComponentTreeUI(this);
    }

    // metodo per la creazione dei pulsanti
    private JButton creaButton(String nome) {
        JButton button = new JButton(nome);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                potere(nome);
            }
        });

        button.setBounds(200, 100, 200, 200); // TODO: da sistemare coordinate bottoni

        return button;
    }

    // metodo per disegnare la mappa, la minimappa, le navi e le stringhe
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // if (condivisa.stato == 1)
        // energia += 2;

        disegnaMappa(g2, 0, mappa);

        Font f = Costanti.FONT;
        g2.setFont(f);
        g2.setColor(Color.BLACK);

        if (condivisa.stato == 1){
            g2.drawString("SCEGLI DOVE ATTACCARE:", Costanti.WIDTH / 2, Costanti.HEIGHT);}
        else if (condivisa.stato == 2) {
            g2.drawString("ASPETTA IL TURNO", Costanti.WIDTH / 2, Costanti.HEIGHT);
            g2.drawString("DELL'AVVERSARIO", Costanti.WIDTH / 2, Costanti.HEIGHT * 4 / 3);
        }

        g2.drawString("Radar 3x3: costo 3", Costanti.WIDTH / 2, Costanti.HEIGHT * 4 );
        g2.drawString("Bommba 2x2: costo 4", Costanti.WIDTH / 2, Costanti.HEIGHT * 5 );
        g2.drawString("(Cliccare sulla cella ", Costanti.WIDTH / 2, Costanti.HEIGHT * 6 );
        g2.drawString("in alto a sinistra)", Costanti.WIDTH / 2, Costanti.HEIGHT * 7 );
        g2.drawString("Bommba 3x3: costo 9", Costanti.WIDTH / 2, Costanti.HEIGHT * 8 );

        disegnaMinimappa(g2);

        g2.drawString("LA TUA FLOTTA:", Costanti.MINIMAP_X, Costanti.HEIGHT);

        g2.drawString("Energia: " + energia, Costanti.WIDTH / 2, Costanti.HEIGHT * 2);

        for (int i = 0; i < listCoordRadar.size(); i++)
            g2.drawString(listNumRadar.get(i).toString(), (int) listCoordRadar.get(i).getX(),
                    (int) listCoordRadar.get(i).getY());

        // gestione scritta vittoria
        if (isVittoria != null) {
            Font f2 = new Font("Congenial Black", Font.BOLD, 100 * Costanti.screenSize.width / 1920);
            g2.setFont(f2);
            if (isVittoria)
                g2.drawString("HAI VINTO!!", (int) Costanti.screenSize.getWidth() / 2,
                        (int) Costanti.screenSize.getHeight() / 2);
            else if (!isVittoria)
                g2.drawString("HAI PERSO", (int) Costanti.screenSize.getWidth() / 2,
                        (int) Costanti.screenSize.getHeight() / 2);
        }

        disegnaLegenda(g2);
    }

    // gestione del click del mouse sulla mappa
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Costanti.MAP.contains(e.getPoint()) && condivisa.stato != 2) {
            Point minimapPoint = Minimappa.getMapPoint(e.getPoint(), 0);

            if (mappa.minimappa.get(minimapPoint.y).get(minimapPoint.x) == 0 || isBombing || isRadar) {

                listSpari.add(e.getPoint());

                if (isBombing || isAirAttack) {
                    if (minimapPoint.x != 9)
                        listSpari.add(new Point(e.getPoint().x + 100, e.getPoint().y));
                    if (minimapPoint.y != 9)
                        listSpari.add(new Point(e.getPoint().x, e.getPoint().y + 100));
                    if (listSpari.size() == 3)
                        listSpari.add(new Point(e.getPoint().x + 100, e.getPoint().y + 100));
                    comando = new String("bomba");
                }

                if (isAirAttack) {
                    if (minimapPoint.x != 0)
                        listSpari.add(new Point(e.getPoint().x - 100, e.getPoint().y));
                    if (minimapPoint.y != 0)
                        listSpari.add(new Point(e.getPoint().x, e.getPoint().y - 100));
                    if (minimapPoint.x != 0 && minimapPoint.y != 0)
                        listSpari.add(new Point(e.getPoint().x - 100, e.getPoint().y - 100));
                    if (minimapPoint.x != 9 && minimapPoint.y != 0)
                        listSpari.add(new Point(e.getPoint().x + 100, e.getPoint().y - 100));
                    if (minimapPoint.x != 0 && minimapPoint.y != 9)
                        listSpari.add(new Point(e.getPoint().x - 100, e.getPoint().y + 100));
                    comando = new String("aereo");
                }

                if (isRadar) {
                    comando = new String("radar");
                }

                if (comando == null)
                    comando = new String("sparo");
            }
        }
    }

    // gestione delle abilità speciali
    private void potere(String nome) {
        if (condivisa.stato != 2) {
            if (nome == "BOMBA" && energia >= 4) {
                isBombing = true;
                energia -= 4;
            } else if (nome == "RADAR" && energia >= 3) {
                isRadar = true;
                energia -= 3;
            } else if (energia >= 9) {
                isAirAttack = true;
                energia -= 9;
            }
        }
    }

    public void setRisultati(Boolean[] spari, String[] navi, Integer radarNum, Boolean fine, List<Point> puntiDifesa) {
        if (fine != null) {
            if (fine)
                isVittoria = true;
            else
                isVittoria = false;
        }

        if (radarNum == null) {
            if (puntiDifesa == null) {
                for (int i = 0; i < spari.length; i++) {
                    Point minimapPoint = Minimappa.getMapPoint(listSpari.get(i), 0);
                    if (spari[i])
                        mappa.minimappa.get(minimapPoint.y).set(minimapPoint.x, 1);
                    else if(mappa.minimappa.get(minimapPoint.y).get(minimapPoint.x) == 0)
                        mappa.minimappa.get(minimapPoint.y).set(minimapPoint.x, -1);
                }

                if (navi != null && !navi[0].equals("tutte")) {
                    for (String string : navi) {
                        String[] values = string.split(",");

                        Point p = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));

                        mappa.minimappa.get(p.y).set(p.x, 2);
                    }
                }
            } else {
                for (int i = 0; i < spari.length; i++) {
                    if (spari[i]) {
                        condivisa.mappaDifesa.minimappa.get(puntiDifesa.get(i).y).set(puntiDifesa.get(i).x, -1);
                        energia += 1; // AGGIUNGI 1 ENERGIA OGNI VIOLTA CHE MI VIENE COLPITA UNA NAVE
                    } else if (condivisa.mappaDifesa.minimappa.get(puntiDifesa.get(i).y).get(puntiDifesa.get(i).x) == 0)
                        condivisa.mappaDifesa.minimappa.get(puntiDifesa.get(i).y).set(puntiDifesa.get(i).x, -2);
                }

                if (navi != null && !navi[0].equals("tutte")) {
                    for (String string : navi) {
                        String[] values = string.split(",");

                        Point p = new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));

                        condivisa.mappaDifesa.minimappa.get(p.y).set(p.x, -3);
                    }
                }
            }

            isBombing = false;
            isAirAttack = false; // resettare AirAttack
        } else {// VALORE UTILIZZATO PER DIRE SE SONO IN DIFESA, IN QUESTO CASO NON SERVE NELLA
                // GRAFICA
            if (condivisa.stato == 1) {
                Point minimapPoint = Minimappa.getMapPoint(listSpari.get(0), 0);
                mappa.puntiRadar.add(new Point(minimapPoint.x, minimapPoint.y));
                mappa.valoriRadar.add(radarNum);
                isRadar = false;
            }/* else {
                condivisa.mappaDifesa.puntiRadar.add(new Point(puntiDifesa.get(0).x, puntiDifesa.get(0).y));
                condivisa.mappaDifesa.valoriRadar.add(radarNum);
            }*/
        }

        listSpari.clear();
        comando = null;

        repaint();
    }
}
