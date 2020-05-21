package ile.view;

import ile.Observer;
import ile.controller.InventoryController;
import ile.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InventoryView extends JPanel implements Observer {
    private Model model;

    private final static int WIDTH = 50*Model.LONGUEUR;
    private final static int HEIGHT = 40*Model.LONGUEUR;
    private final static int SIDE = Model.LONGUEUR*4;
    private final static int inSIDE = SIDE/2;
    private final static int OV = 10;
    private static int margin;

    private int mx;
    private int my;

    private BufferedImage keyWater = ImageIO.read(new File("src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("src/files/kearth.JPG"));

    private BufferedImage one = ImageIO.read(new File("src/files/1.PNG"));
    private BufferedImage two = ImageIO.read(new File("src/files/2.PNG"));
    private BufferedImage three = ImageIO.read(new File("src/files/3.PNG"));
    private BufferedImage four = ImageIO.read(new File("src/files/4.PNG"));

    private BufferedImage[] imageElement = new BufferedImage[4];
    private BufferedImage[] imagePlayer = new BufferedImage[6];

    private JLabel title;

    /**
     * Classe interne pour dessiner des cases invisibles correspondant aux zones clickables des clés
     */
    public class Case {
        private int x;
        private int y;
        private int width;
        private int height;
        private Key key;

        /**
         * Constructeur de la classe interne Case
         * @param x la coordonnée x de la Case
         * @param y la coordonnée y de la Case
         * @param width la longueur de la Case
         * @param height la hauteur de la Case
         * @param k la clé qui se trouve de la Case
         */
        public Case(int x, int y, int width, int height, Key k) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.key = k;
        }

        /**
         * retourne la clé contenue dans la case
         * @return key
         */
        public Key getCaseKey() {
            return this.key;
        }

        /**
         * Renvoie true si le click de la souris est dans la zone clickable
         * @param a abscisse enregistrée au moment du click
         * @param b ordonnée enregistrée au moment du click
         * @return bool
         */
        public boolean inCase(int a, int b) {
            return (a >= this.x && a <= (this.x + this.width) && b >= this.y && b <= (this.y + this.height));
        }
    }

    private ArrayList<ArrayList<Case>> takeCases;
    private Case[] dropCases;

    /**
     * Constructeur de InventoryView
     * @param model le modèle du programme
     * @throws IOException
     */
    public InventoryView(Model model) throws IOException {
        this.model = model;
        this.mx = 0;
        this.my = 0;
        this.setLayout(null);

        this.imageElement[0] = ImageIO.read(new File("src/files/artefact_air.png"));
        this.imageElement[1] = ImageIO.read(new File("src/files/artefact_water.png"));
        this.imageElement[2] = ImageIO.read(new File("src/files/artefact_fire.png"));
        this.imageElement[3] = ImageIO.read(new File("src/files/artefact_earth.png"));


        this.imagePlayer[0] = ImageIO.read(new File("src/files/explo.JPG"));
        this.imagePlayer[1] = ImageIO.read(new File("src/files/inge.JPG"));
        this.imagePlayer[2] = ImageIO.read(new File("src/files/messager.JPG"));
        this.imagePlayer[3] = ImageIO.read(new File("src/files/nautilus.JPG"));
        this.imagePlayer[4] = ImageIO.read(new File("src/files/pilote.JPG"));
        this.imagePlayer[5] = ImageIO.read(new File("src/files/plongeur.JPG"));


        model.addObserver(this);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);

        this.title = new JLabel("Inventaire");
        this.add(this.title);
        Dimension size = this.title.getPreferredSize();
        this.title.setBounds(WIDTH / 2, HEIGHT / 30, size.width, size.height);

        this.title.setVisible(true);

        margin = this.title.getPreferredSize().width + WIDTH / 15;

        this.takeCases = new ArrayList<>();
        ArrayList<Case> t0 = new ArrayList<>();
        ArrayList<Case> t1 = new ArrayList<>();
        ArrayList<Case> t2 = new ArrayList<>();
        ArrayList<Case> t3 = new ArrayList<>();
        this.takeCases.add(t0);
        this.takeCases.add(t1);
        this.takeCases.add(t2);
        this.takeCases.add(t3);
        this.dropCases = new Case[4];
        for (int i = 0; i < dropCases.length; i++) {
            dropCases[i] = new Case(WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), WIDTH - SIDE, HEIGHT / this.model.getPlayers().size(), Key.Air);
        }
        InventoryController ctrl = new InventoryController(this.model, this);
        addMouseListener(ctrl);
    }

    /**
     * L'interface [Observer] demande de fournir une méthode [update], qui
     * sera appelée lorsque la vue sera notifiée d'un changement dans le
     * modèle. Ici on se content de réafficher toute la grille avec la méthode
     * prédéfinie [repaint].
     */
    public void update() {
        repaint();
    }

    /**
     * Fonction de dessin principale
     * @param g
     */
    public void paint(Graphics g){
        super.paint(g);
        this.getParent().repaint();
        //players
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            if (this.model.getPlayers().get(i) instanceof PlayerExplorator) {
                g.drawImage(imagePlayer[0],WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE, this);
            } else if (this.model.getPlayers().get(i) instanceof PlayerEngineer) {
                g.drawImage(imagePlayer[1],WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE, this);
            } else if (this.model.getPlayers().get(i) instanceof PlayerMessenger) {
                g.drawImage(imagePlayer[2],WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE,this);
            } else if (this.model.getPlayers().get(i) instanceof PlayerNautilus) {
                g.drawImage(imagePlayer[3], WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE, this);
            } else if (this.model.getPlayers().get(i) instanceof PlayerPilote) {
                g.drawImage(imagePlayer[4], WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE, this);
            } else {
                g.drawImage(imagePlayer[5], WIDTH / 15, (i + 1) * HEIGHT / this.model.getPlayers().size(), SIDE, SIDE, this);
            }
        }

        //key
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            for (int j = 0; j < this.model.getPlayers().get(i).getKey().size(); j++) {
                fillTakeCase();
                switch (this.model.getPlayers().get(i).getKey().get(j)) {
                    case Water:
                        int gigi1 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Water) + 1);
                        g.drawImage(this.keyWater, (gigi1) * margin + (gigi1 - 1) * SIDE, (i + 1) * (HEIGHT / this.model.getPlayers().size()) + SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Water) == 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(one,gigi1 * margin + (gigi1 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Water) == 2){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(two,gigi1 * margin + (gigi1 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Water) == 3){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(three,gigi1 * margin + (gigi1 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Water) == 4){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(four,gigi1 * margin + (gigi1 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Water) > 4){
                            g.setColor(new Color(255, 0, 3, 180));
                            g.drawImage(four,gigi1 * margin + (gigi1 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi1 * margin + (gigi1 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }
                        break;
                    case Earth:
                        int gigi2 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Earth) + 1);
                        g.drawImage(this.keyEarth, (gigi2) * margin + (gigi2 - 1) * SIDE, (i + 1) * (HEIGHT / this.model.getPlayers().size()) + SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Earth) == 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(one,gigi2 * margin + (gigi2 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi2 * margin + (gigi2 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Earth) == 2){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(two,gigi2 * margin + (gigi2 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi2 * margin + (gigi2 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Earth) == 3){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(three,gigi2 * margin + (gigi2 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi2 * margin + (gigi2 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Earth) == 4){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(four,gigi2 * margin + (gigi2 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi2 * margin + (gigi2 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Earth) > 4){
                            g.setColor(new Color(255, 0, 3, 180));
                            g.drawImage(four,gigi2 * margin + (gigi2 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi2 * margin + (gigi2 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }
                        break;
                    case Fire:
                        int gigi3 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Fire) + 1);
                        g.drawImage(this.keyFire, (gigi3) * margin + (gigi3 - 1) * SIDE, (i + 1) * (HEIGHT / this.model.getPlayers().size()) + SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Fire) == 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(one,gigi3 * margin + (gigi3 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi3 * margin + (gigi3 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Fire) == 2){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(two,gigi3 * margin + (gigi3 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi3 * margin + (gigi3 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Fire) == 3){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(three,gigi3 * margin + (gigi3 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi3 * margin + (gigi3 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Fire) == 4){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(four,gigi3 * margin + (gigi3 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi3 * margin + (gigi3 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Fire) > 4){
                            g.setColor(new Color(255, 0, 3, 180));
                            g.drawImage(four,gigi3 * margin + (gigi3 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi3 * margin + (gigi3 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }
                        break;
                    case Air:
                        int gigi4 = (this.model.getPlayers().get(i).positionKey().indexOf(Key.Air) + 1);
                        g.drawImage(this.keyAir, (gigi4) * margin + (gigi4 - 1) * SIDE, (i + 1) * (HEIGHT / this.model.getPlayers().size()) + SIDE, SIDE, SIDE, this);
                        if (this.model.getPlayers().get(i).numberKeys(Key.Air) == 1) {
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(one,gigi4 * margin + (gigi4 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi4 * margin + (gigi4 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Air) == 2){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(two,gigi4 * margin + (gigi4 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi4 * margin + (gigi4 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Air) == 3){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(three,gigi4 * margin + (gigi4 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi4 * margin + (gigi4 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Air) == 4){
                            g.setColor(new Color(255, 255, 255, 180));
                            g.drawImage(four,gigi4 * margin + (gigi4 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi4 * margin + (gigi4 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }else if (this.model.getPlayers().get(i).numberKeys(Key.Air) > 4){
                            g.setColor(new Color(255, 0, 3, 180));
                            g.drawImage(four,gigi4 * margin + (gigi4 - 1) * SIDE,(i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE,30,30,this);
                            g.fill3DRect(gigi4 * margin + (gigi4 - 1) * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, 30, 30, true);
                        }
                        break;
                }
            }
        }
        //artifact
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(192, 192, 192, 125));
            g.fill3DRect((i + 1) * WIDTH / 5, HEIGHT / 30 + SIDE / 2, SIDE, SIDE, true);
        }
        for (int i = 0; i < this.model.getArtifacts().size(); i++) {
            switch (this.model.getArtifacts().get(i)) {
                case Air:
                    g.drawImage(imageElement[0], WIDTH / 5, HEIGHT / 30 + SIDE / 2, SIDE, SIDE, this);
                    break;
                case Fire:
                    g.drawImage(imageElement[2], 2 * WIDTH / 5, HEIGHT / 30 + SIDE / 2, SIDE, SIDE, this);
                    break;
                case Earth:
                    g.drawImage(imageElement[3], 3 * WIDTH / 5, HEIGHT / 30 + SIDE / 2, SIDE, SIDE, this);
                    break;
                case Water:
                    g.drawImage(imageElement[1], 4 * WIDTH / 5, HEIGHT / 30 + SIDE / 2, SIDE, SIDE, this);
            }
        }

        //hand
        try{
            Point p = this.getMousePosition();
            mx = p.x;
            my = p.y;
        } catch (NullPointerException e) {
        }

        for (ArrayList<Case> player: this.takeCases) {
            for (Case c : player) {
                if (c.inCase(mx, my)) {
                    g.setColor(new Color(255, 255, 51));
                    g.fill3DRect(c.x-OV/2, c.y-OV/2, SIDE+OV, SIDE+OV, true);
                    switch (c.getCaseKey()) {
                        case Fire:
                            g.drawImage(keyFire, c.x, c.y, SIDE, SIDE, this);
                            break;
                        case Water:
                            g.drawImage(keyWater, c.x, c.y, SIDE, SIDE, this);
                            break;
                        case Earth:
                            g.drawImage(keyEarth, c.x, c.y, SIDE, SIDE, this);
                            break;
                        case Air:
                            g.drawImage(keyAir, c.x, c.y, SIDE, SIDE, this);
                            break;
                    }
                }
            }
        }

        //colorie en vert la case de drop si on peut y lacher la clé en main
        if (this.model.getHand().hasKey()) {
            switch (this.model.getHand().getKey().get(0)) {
                case Fire:
                    g.drawImage(keyFire, mx, my, SIDE, SIDE, this);
                    break;
                case Water:
                    g.drawImage(keyWater, mx, my, SIDE, SIDE, this);
                    break;
                case Earth:
                    g.drawImage(keyEarth, mx, my, SIDE, SIDE, this);
                    break;
                case Air:
                    g.drawImage(keyAir, mx, my, SIDE, SIDE, this);
                    break;
            }
            for (int i = 0 ; i < this.dropCases.length ; i++) {
                if (this.dropCases[i].inCase(mx, my)) {
                    if (this.model.getHand().isNearby(this.model.getPlayers().get(i).getArea()) || this.model.getHand().getFlying())
                        g.setColor(new Color(0,200, 0, 100));
                        g.fill3DRect(this.dropCases[i].x, this.dropCases[i].y,WIDTH-SIDE,HEIGHT/this.model.getPlayers().size(),false);
                }
            }
        }
    }

    /**
     * Creer une nouvelle zone clickable pour chaque type de clé si la clé n'a pas déjà été dessinée
     */
    public void fillTakeCase() {
        boolean refill = false;
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            for (int j = 0; j < this.model.getPlayers().get(i).positionKey().size(); j++) {
                for (Case c : this.takeCases.get(i)) {
                    if (c.getCaseKey() == this.model.getPlayers().get(i).positionKey().get(j)) {
                        refill = true;
                        break;
                    }
                }
                if (!refill) {
                    this.takeCases.get(i).add(new Case((j + 1) * margin + j * SIDE, (i + 1) * HEIGHT / this.model.getPlayers().size() + SIDE, SIDE, SIDE, this.model.getPlayers().get(i).positionKey().get(j)));
                }
                refill = false;
            }
        }
    }

    /**
     * Retourne les zones clickables des clés
     * @return ArrayList<ArrayList<Case>>
     */

    public ArrayList<ArrayList<Case>> getTakeCases() {
        return this.takeCases;
    }

    /**
     * Retourne les zones clickables où l'on peut lacher les clés
     * @return Case[]
     */
    public Case[] getDropCases() {
        return dropCases;
    }
}
