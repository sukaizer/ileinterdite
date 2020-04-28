package ile.view;

import ile.Observer;
import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class InventoryView extends JPanel implements Observer {
    private Model model;
    private final static int WIDTH = 50*Model.LONGUEUR;
    private final static int HEIGHT = 200;

    private BufferedImage keyWater = ImageIO.read(new File("src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("src/files/kearth.JPG"));

    private JLabel title;
    private JLabel player1;
    private JLabel player2;
    private JLabel player3;
    private JLabel player4;

    public InventoryView(Model model) throws IOException {
        this.model = model;
        model.addObserver(this); //pas sur
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);
        this.setLayout(null);
        this.title = new JLabel("Inventaire");
        this.add(this.title);
        Dimension size = this.title.getPreferredSize();
        this.title.setBounds(WIDTH/2, HEIGHT/2 , size.width, size.height);
        this.title.setVisible(true);
    }

    @Override
    public void update() {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
    }
}
