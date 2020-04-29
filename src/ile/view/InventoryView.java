package ile.view;

import ile.Observer;
import ile.model.Key;
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
    private final static int SIDE = Model.LONGUEUR*4;

    private BufferedImage keyWater = ImageIO.read(new File("src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("src/files/kearth.JPG"));

    /*
    private BufferedImage keyWater = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kearth.JPG"));
*/
    private BufferedImage[] imageElement = new BufferedImage[4];

    private JLabel title;
    private ArrayList<JLabel> players;

    public InventoryView(Model model) throws IOException {
        this.model = model;
        this.setLayout(null);


        this.imageElement[0] = ImageIO.read(new File("src/files/air.PNG"));
        this.imageElement[1] = ImageIO.read(new File("src/files/water.PNG"));
        this.imageElement[2] = ImageIO.read(new File("src/files/fire.PNG"));
        this.imageElement[3] = ImageIO.read(new File("src/files/earth.PNG"));


/*
        this.imageElement[0] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/air.png"));
        this.imageElement[1] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/water.png"));
        this.imageElement[2] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/fire.png"));
        this.imageElement[3] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/earth.png"));
*/
        model.addObserver(this);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);

        this.title = new JLabel("Inventaire");
        this.add(this.title);
        Dimension size = this.title.getPreferredSize();
        this.title.setBounds(WIDTH/2, HEIGHT/30 , size.width, size.height);

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


//les JLabels du nb de clés se mettent derrière les graphics
//problème sur update de graphics et de JLabel
    //pas de doublons dans getKeys
    //le bons nombre de clés
    //conditions des ifs bien remplies
    public void paintComponent(Graphics g){
        //super.paint(g);
        //key
        int margin = this.title.getPreferredSize().width + WIDTH/15;
        for (int i = 0 ; i < this.model.getPlayers().size(); i++) {
            for (int j = 0 ; j < this.model.getPlayers().get(i).getKey().size(); j++) {
                switch(this.model.getPlayers().get(i).getKey().get(j)) {
                    case Water:
                        int gigi1 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Water)+1);
                        g.drawImage(this.keyWater, (gigi1) * margin + (gigi1-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Water) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, 30, 30, true);
                            JLabel w = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Water));
                            w.setBounds(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, SIDE, SIDE);
                            this.add(w);
                        }
                        break;
                    case Earth:
                        int gigi2 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Earth)+1);
                        g.drawImage(this.keyEarth, (gigi2)*margin + (gigi2-1)*SIDE, (i+1)*HEIGHT/this.model.getPlayers().size() + 40, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Earth) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi2*margin + (gigi2-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, 30, 30, true);
                            JLabel e = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Water));
                            e.setBounds((gigi2) * margin + (gigi2-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, SIDE, SIDE);
                            this.add(e);
                        }
                        break;
                    case Fire:
                        int gigi3 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Fire)+1) ;
                        g.drawImage(this.keyFire, (gigi3)*margin + (gigi3-1)*SIDE, (i+1)*HEIGHT/this.model.getPlayers().size() + 40, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Fire) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi3*margin + (gigi3-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, 30, 30, true);
                            JLabel f = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Fire));
                            f.setBounds((gigi3) * margin + (gigi3-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, SIDE, SIDE);
                            this.add(f);
                        }
                        break;
                    case Air:
                        int gigi4 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Air)+1) ;
                        g.drawImage(this.keyAir, (gigi4)*margin + (gigi4-1)*SIDE, (i+1)*HEIGHT/this.model.getPlayers().size() + 40, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Air) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi4*margin + (gigi4-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, 30, 30, true);
                            JLabel a = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Air));
                            a.setBounds((gigi4) * margin + (gigi4-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + 40, SIDE, SIDE);
                            this.add(a);
                        }
                       break;
                }
            }
        }
        //artifact
        for (int i = 0 ; i < 4 ; i++) {
            g.setColor(new Color(192,192,192, 125));
            g.fill3DRect((i+1)*WIDTH/5, HEIGHT/30+SIDE/2, SIDE, SIDE, true);
        }
        for (int i = 0 ; i < this.model.getArtifacts().size(); i++) {
            switch(this.model.getArtifacts().get(i)) {
                case Air:
                    g.drawImage(imageElement[0], WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
                    break;
                case Fire:
                    g.drawImage(imageElement[2], 2*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
                    break;
                case Earth:
                    g.drawImage(imageElement[3], 3*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
                    break;
                case Water:
                    g.drawImage(imageElement[1], 4*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
            }
        }
    }
}
