package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Fase1 extends MyPanel {
    private Point initialPoint = null;
    private Nave initialNave = null;
    private int idRotazione = -1;
    private JButton btnRotazioneSx, btnRotazioneDx;

    public Fase1() throws IOException {
        //BufferedImage buttonIcon = ImageIO.read(new File("\\frecciaSx.jpg")); non trova le immagini
        btnRotazioneSx = creaButton("sinistra", true);
        btnRotazioneDx = creaButton("destra",false);

        this.add(btnRotazioneDx);
        this.add(btnRotazioneSx);
    }

    //non fa partire il programma
    private JButton creaButton(String nome, boolean isSx) {
        JButton button = new JButton(nome);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cond.listaNavi.flotta.get(idRotazione).ruotaNave((isSx ? -1 : 1));  //-1 a Sx; 1 a Dx
                repaint();
            }
        });
        
        button.setBounds(100, 100, 200, 200);  //da sistemare una volta che il programma funziona

        return button;
    }

    public void paint(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        disegnaMappa(g2, 100);

        disegnaNavi(g2);

        int fontSize = Costanti.FONT;
        Font f = new Font("Berlin Sans FB", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.BLACK);
        g2.drawString("INSERISCI LE TUE NAVI:", Costanti.WIDTH / 2, Costanti.HEIGHT);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (cond.nave != -1) {
            if (initialPoint == null)
                initialPoint = e.getPoint();

            boolean isFirstTouch = false;
            for (int i = 0; i < cond.listaNavi.flotta.get(cond.nave - 1).coordinate.size(); i++) {
                Point coord = cond.listaNavi.flotta.get(cond.nave - 1).coordinate.get(i);

                if (initialNave == null) {
                    isFirstTouch = true;
                    initialNave = new Nave(cond.listaNavi.flotta.get(cond.nave - 1).lunghezza, -1);
                    idRotazione = initialNave.numero;
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

    @Override
    public void mouseReleased(MouseEvent e) {
        if (initialPoint != null && initialNave != null) {
            initialPoint = null;
            initialNave = null;
        }
        if (cond.nave != -1)
            cond.nave = -1;
    }

}
