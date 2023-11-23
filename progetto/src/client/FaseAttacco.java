package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

// classe dove viene gestita la fase di attacco e difesa della partita
public class FaseAttDif extends MyPanel {
    public static Minimappa mappaAttacco = new Minimappa(); // 0: cella ignota; -1 cella vuota; 1: cella colpita
    private int energia = 0; // energia disponibile per le abilità speciali
    private JButton btnBomba, btnRadar; // pulsanti per le abilità speciali
    private Boolean isBombing = false; // true se viene premuto il pulsante per la bomba con abbastanza energia

    public FaseAttDif() {
        btnBomba = creaButton("BOMBA", true);
        btnRadar = creaButton("RADAR", false);

        this.add(btnBomba);
        this.add(btnRadar);

        SwingUtilities.updateComponentTreeUI(this);
    }

    // metodo per la creazione dei pulsanti
    private JButton creaButton(String nome, boolean isSx) {
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

        disegnaMappa(g2, 0, mappaAttacco);

        Font f = Costanti.FONT;
        g2.setFont(f);
        g2.setColor(Color.BLACK);

        if (condivisa.stato == 1)
            g2.drawString("SCEGLI DOVE ATTACCARE:", Costanti.WIDTH / 2, Costanti.HEIGHT);
        else if (condivisa.stato == 2) {
            g2.drawString("ASPETTA IL TURNO", Costanti.WIDTH / 2, Costanti.HEIGHT);
            g2.drawString("DELL'AVVERSARIO", Costanti.WIDTH / 2, Costanti.HEIGHT * 4 / 3);
        }

        disegnaMinimappa(g2);

        g2.drawString("LA TUA FLOTTA:", Costanti.MINIMAP_X, Costanti.HEIGHT);

        g2.drawString("Energia: " + energia, Costanti.WIDTH / 2, Costanti.HEIGHT * 2);
    }

    // gestione del click del mouse sulla mappa
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Costanti.MAP.contains(e.getPoint()) && condivisa.stato != 2) {
            Point minimapPoint = Minimappa.getMapPoint(e.getPoint(), 0);

            if (mappaAttacco.minimappa.get(minimapPoint.y).get(minimapPoint.x) != -1) {
                condivisa.stato = 2;

                if (isBombing) {
                    // TODO: dire al server che è stat lanciata una bomba
                }

                // TODO: invia messaggio al server
                // TODO: con la risposta settare sulla minimappa se il colpo è a vuoto o meno
                energia += 2;
            }
        }
    }

    // gestione delle abilità speciali
    private void potere(String nome) {
        if (nome == "BOMBA" && energia == 0) {
            isBombing = true;
            // TODO: scegliere
            energia -= 0;// TODO: cambiare valori
        } else if (energia == 0) {
            // TODO: invio al server che ho premuto radar
            energia -= 0;
        }
    }
}
