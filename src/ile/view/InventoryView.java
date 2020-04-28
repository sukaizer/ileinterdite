package ile.view;

import ile.Observer;
import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class InventoryView extends JPanel implements Observer {
    private Model model;
    private final static int WIDTH = 50*Model.LONGUEUR;
    private final static int HEIGHT = 40*Model.LONGUEUR;

    /*
    private BufferedImage keyWater = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kearth.JPG"));
    */

    private BufferedImage keyWater = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kearth.JPG"));

    private JLabel title;
    private ArrayList<JLabel> players;

    public InventoryView(Model model) throws IOException {
        this.model = model;
        this.setLayout(null);
        model.addObserver(this);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);

        this.title = new JLabel("Inventaire");
        this.add(this.title);
        Dimension size = this.title.getPreferredSize();
        this.title.setBounds(WIDTH/2, HEIGHT/10 , size.width, size.height);

        this.title.setVisible(true);
        this.players = new ArrayList<>();
        for (int i = 0 ; i < this.model.getPlayers().size() ; i++) {
            this.players.add(new JLabel("Joueur " + (i+1)));
            this.add(this.players.get(i));
            this.players.get(i).setBounds(WIDTH/15, (i+1)*HEIGHT/this.model.getPlayers().size(), size.width, size.height);
        }
    }

    @Override
    public void update() {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0 ; i < this.model.getPlayers().size(); i++) {
            for (int j = 0 ; j < this.model.getPlayers().get(i).getKey().size(); j++) {
                int l = 0;
            }
        }
    }
}
