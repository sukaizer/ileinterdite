package ile.view;

import ile.Observer;
import ile.controller.InventoryController;
import ile.model.Key;
import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.imageio.ImageIO;



public class InventoryView extends JPanel implements Observer{
    private Model model;
    private final static int WIDTH = 50*Model.LONGUEUR;
    private final static int HEIGHT = 40*Model.LONGUEUR;
    private final static int SIDE = Model.LONGUEUR*4;
    private final static int inSIDE = SIDE/2;

    private static int margin;

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

    private BufferedImage[] imageElement = new BufferedImage[4];

    private JLabel title;
    private ArrayList<JLabel> players;

    public class Case {
        private int x;
        private int y;
        private int SIDE;
        private Key key;

        public Case(int x, int y, int side, Key k) {
            this.x = x;
            this.y = y;
            this.SIDE = side;
            this.key = k;
        }

        public Case(int x, int y, int side, int key){
            this.x = x;
            this.y = y;
            this.SIDE = side;
            switch (key){
                case 0:
                    this.key = Key.Air;
                    break;
                case 1:
                    this.key = Key.Water;
                    break;
                case 2:
                    this.key = Key.Fire;
                    break;
                case 3:
                    this.key = Key.Earth;
                    break;
            }
        }

        public Key getCaseKey() {
            return this.key;
        }

        public boolean inCase(int a, int b) {
            return (a >= this.x && a <= (this.x+this.SIDE) && b >= this.y && b <= (this.y+this.SIDE));
        }
    }

    private ArrayList<ArrayList<Case>> takeCases;
    private ArrayList<ArrayList<Case>> dropCases;
    public InventoryView(Model model) throws IOException {
        this.model = model;
        this.setLayout(null);



        /*
        this.imageElement[0] = ImageIO.read(new File("src/files/air.PNG"));
        this.imageElement[1] = ImageIO.read(new File("src/files/water.PNG"));
        this.imageElement[2] = ImageIO.read(new File("src/files/fire.PNG"));
        this.imageElement[3] = ImageIO.read(new File("src/files/earth.PNG"));
*/


        this.imageElement[0] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/air.png"));
        this.imageElement[1] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/water.png"));
        this.imageElement[2] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/fire.png"));
        this.imageElement[3] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/earth.png"));

        model.addObserver( this);
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

        this.margin = this.title.getPreferredSize().width + WIDTH/15;

        this.takeCases = new ArrayList<>();
        ArrayList<Case> t0 = new ArrayList<>();
        ArrayList<Case> t1 = new ArrayList<>();
        ArrayList<Case> t2 = new ArrayList<>();
        ArrayList<Case> t3 = new ArrayList<>();
        this.takeCases.add(t0);
        this.takeCases.add(t1);
        this.takeCases.add(t2);
        this.takeCases.add(t3);
        this.dropCases = new ArrayList<>();
        ArrayList<Case> d0 = new ArrayList<>();
        ArrayList<Case> d1 = new ArrayList<>();
        ArrayList<Case> d2 = new ArrayList<>();
        ArrayList<Case> d3 = new ArrayList<>();
        this.dropCases.add(d0);
        this.dropCases.add(d1);
        this.dropCases.add(d2);
        this.dropCases.add(d3);

        InventoryController ctrl = new InventoryController(this.model, this);
        addMouseListener(ctrl);
    }

    public void update() {
        repaint();
    }


//les JLabels du nb de clés se mettent derrière les graphics
// problème sur update de graphics et de JLabel
    //pas de doublons dans getKeys
    //le bons nombre de clés
    //conditions des ifs bien remplies
    public void paint(Graphics g){
        super.paint(g);
        //key
        for (int i = 0 ; i < this.model.getPlayers().size(); i++) {
            for (int j = 0 ; j < this.model.getPlayers().get(i).getKey().size(); j++) {
                fillTakeCase();
                switch(this.model.getPlayers().get(i).getKey().get(j)) {
                    case Water:
                        int gigi1 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Water)+1);
                        g.drawImage(this.keyWater, (gigi1) * margin + (gigi1-1) * SIDE, (i + 1) * (HEIGHT / this.model.getPlayers().size())+SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Water) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                            JLabel w = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Water));
                            w.setBounds(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, SIDE);
                            this.add(w);
                        }
                        break;
                    case Earth:
                        int gigi2 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Earth)+1);
                        g.drawImage(this.keyEarth, (gigi2)*margin + (gigi2-1)*SIDE, (i+1)*(HEIGHT / this.model.getPlayers().size())+SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Earth) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi2*margin + (gigi2-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                            JLabel e = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Water));
                            e.setBounds((gigi2) * margin + (gigi2-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, SIDE);
                            this.add(e);
                        }
                        break;
                    case Fire:
                        int gigi3 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Fire)+1) ;
                        g.drawImage(this.keyFire, (gigi3)*margin + (gigi3-1)*SIDE, (i+1)*(HEIGHT / this.model.getPlayers().size())+SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Fire) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi3*margin + (gigi3-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                            JLabel f = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Fire));
                            f.setBounds((gigi3) * margin + (gigi3-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, SIDE);
                            this.add(f);
                        }
                        break;
                    case Air:
                        int gigi4 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Air)+1) ;
                        g.drawImage(this.keyAir, (gigi4)*margin + (gigi4-1)*SIDE, (i+1)*(HEIGHT / this.model.getPlayers().size())+SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Air) > 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.fill3DRect(gigi4*margin + (gigi4-1)*SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                            JLabel a = new JLabel(""+this.model.getPlayers().get(i).numberKeys(Key.Air));
                            a.setBounds((gigi4) * margin + (gigi4-1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, SIDE);
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
                    g.drawImage(imageElement[1], 2*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
                    break;
                case Earth:
                    g.drawImage(imageElement[2], 3*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
                    break;
                case Water:
                    g.drawImage(imageElement[3], 4*WIDTH/5, HEIGHT/30+SIDE/2, SIDE*2, SIDE*2, this);
            }
        }
        //hand
        if (this.model.getHand().hasKey()) {
            for (int i = 0 ; i < this.model.getPlayers().size(); i++) {
                for (int j = 0 ; j < this.imageElement.length ; j++) {
                    g.drawImage(imageElement[j], margin + j*inSIDE, (i+1)*HEIGHT/this.model.getPlayers().size(), inSIDE, inSIDE, this);
                    this.fillDropCases();
                }
            }
        }
    }

    public void fillTakeCase() {
        boolean refill = false;
        for (int i = 0 ; i < this.model.getPlayers().size() ; i++) {
            for (int j = 0 ; j < this.model.getPlayers().get(i).positionKey().size() ; j++) {
                for (Case c : this.takeCases.get(i)){
                    if (c.getCaseKey() == this.model.getPlayers().get(i).positionKey().get(j)) {
                        refill = true;
                        break;
                    }
                }
                if (!refill) {
                    this.takeCases.get(i).add(new Case((j+1) * margin + j* SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, this.model.getPlayers().get(i).positionKey().get(j)));
                }
                refill = false;
            }
        }
    }

    public void fillDropCases() {
        if (this.model.getHand().hasKey()) {
            for (int i = 0; i < this.model.getPlayers().size(); i++) {
                if (this.dropCases.get(i).size() < 4) {
                    for (int j = 0; j < this.imageElement.length; j++) {
                        this.dropCases.get(i).add(new Case(margin + j * inSIDE, (i + 1) * HEIGHT / this.model.getPlayers().size(), inSIDE, j));
                    }
                }
            }
        }
    }


    public ArrayList<ArrayList<Case>> getTakeCases() {
        return this.takeCases;
    }

    public ArrayList<ArrayList<Case>> getDropCases() {
        return dropCases;
    }
}
