import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel  {

    public static int WIDTH = 30, HEIGHT = WIDTH;

    final int PANEL_WIDTH = JMappa.MAXEL_M * WIDTH;
    final int PANEL_HEIGHT = JMappa.MAXEL_M * HEIGHT;

    public MyPanel()  {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D graphic_2D = (Graphics2D) g;

    }
}