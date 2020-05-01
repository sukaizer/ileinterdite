package ile.view;

import ile.Observer;
import ile.controller.Controller;
import ile.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridView extends JPanel implements Observer {
    private Model model;
    private final static int TAILLE = 50;
    private BufferedImage[] imagePlayer = new BufferedImage[6];
    private BufferedImage[] imageElement = new BufferedImage[5];
    private BufferedImage grass;
    private BufferedImage flooded;
    private BufferedImage submerged;
    private View view;

    /**
     * Constructeur de classe GridView
     * @param model modèle lié au programme
     * @param view la vue principale comprenant toutes autres
     * @throws IOException lié aux images
     */
    public GridView(Model model, View view) throws IOException {
        this.model = model;
        this.view = view;
        /** On enregistre la vue [this] en tant qu'observateur de [modele]. */
        model.addObserver(this);
        /**
         * Définition et application d'une taille fixe pour cette zone de
         * l'interface, calculée en fonction du nombre de cellules et de la
         * taille d'affichage.
         */
        Dimension dim = new Dimension(TAILLE * Model.LONGUEUR, TAILLE * Model.LONGUEUR);
        this.setPreferredSize(dim);

        /**
         * chargement des images
         *
         **/

        this.grass = ImageIO.read(new File("src/files/grass.JPG"));
        this.flooded = ImageIO.read(new File("src/files/flooded.JPG"));
        this.submerged = ImageIO.read(new File("src/files/submerged.JPG"));

        this.imagePlayer[0] = ImageIO.read(new File("src/files/explo.JPG"));
        this.imagePlayer[1] = ImageIO.read(new File("src/files/inge.JPG"));
        this.imagePlayer[2] = ImageIO.read(new File("src/files/messager.JPG"));
        this.imagePlayer[3] = ImageIO.read(new File("src/files/nautilus.JPG"));
        this.imagePlayer[4] = ImageIO.read(new File("src/files/pilote.JPG"));
        this.imagePlayer[5] = ImageIO.read(new File("src/files/plongeur.JPG"));

        this.imageElement[0] = ImageIO.read(new File("src/files/air.PNG"));
        this.imageElement[1] = ImageIO.read(new File("src/files/water.PNG"));
        this.imageElement[2] = ImageIO.read(new File("src/files/fire.PNG"));
        this.imageElement[3] = ImageIO.read(new File("src/files/earth.PNG"));
        this.imageElement[4] = ImageIO.read(new File("src/files/h.PNG"));

        /*
        this.grass = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/grass.JPG"));
        this.flooded = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/flooded.jpg"));
        this.submerged = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/submerged.jpg"));

        this.imagePlayer[0] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/explo.JPG"));
        this.imagePlayer[1] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/inge.JPG"));
        this.imagePlayer[2] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/messager.JPG"));
        this.imagePlayer[3] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/nautilus.JPG"));
        this.imagePlayer[4] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/pilote.JPG"));
        this.imagePlayer[5] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/plongeur.JPG"));

        this.imageElement[0] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/air.png"));
        this.imageElement[1] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/water.png"));
        this.imageElement[2] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/fire.png"));
        this.imageElement[3] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/earth.png"));
        this.imageElement[4] = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/h.png"));*/


        //on ajoute un controlleur pour la fenetre principale
        Controller ctrl = new Controller(this.model, new ButtonView(this.model, this, this.view), this, this.view);
        addMouseListener(ctrl);
        addKeyListener(ctrl);
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
    public void paint(Graphics g) {
        for (int i = 1; i <= Model.LONGUEUR; i++) {
            for (int j = 1; j <= Model.LONGUEUR; j++) {
                Area a = model.getArea(i - 1, j - 1);
                int x = (i - 1) * TAILLE;
                int y = (j - 1) * TAILLE;

                if (a.getType().equals(Type.Land)) { //paint des cases normales
                    if (a.getState().equals(State.Normal)) {
                        g.drawImage(grass, x, y, TAILLE, TAILLE, this);
                    } else if (a.getState().equals(State.Flooded)) {
                        g.drawImage(flooded, x, y, TAILLE, TAILLE, this);
                    } else if (a.getState().equals(State.Submerged)) {
                        g.drawImage(submerged, x, y, TAILLE, TAILLE, this);
                    }
                } else { //paint des elements + heliport
                    if (a.getType().equals(Type.Air)) {
                        g.setColor(new Color(159, 160, 255));
                        g.fill3DRect(x, y, TAILLE, TAILLE, true);
                        g.drawImage(imageElement[0], x, y, TAILLE, TAILLE, this);
                    } else if (a.getType().equals(Type.Water)) {
                        g.setColor(new Color(0, 191, 255));
                        g.fill3DRect(x, y, TAILLE, TAILLE, true);
                        g.drawImage(imageElement[1], x, y, TAILLE, TAILLE, this);
                    } else if (a.getType().equals(Type.Fire)) {
                        g.setColor(new Color(255, 102, 0));
                        g.fill3DRect(x, y, TAILLE, TAILLE, true);
                        g.drawImage(imageElement[2], x, y, TAILLE, TAILLE, this);
                    } else if (a.getType().equals(Type.Earth)) {
                        g.setColor(new Color(153, 102, 51));
                        g.fill3DRect(x, y, TAILLE, TAILLE, true);
                        g.drawImage(imageElement[3], x, y, TAILLE, TAILLE, this);
                    } else if (a.getType().equals(Type.Heliport)) {
                        g.setColor(new Color(66, 66, 70, 238));
                        g.fill3DRect(x, y, TAILLE, TAILLE, true);
                        g.drawImage(imageElement[4], x, y, TAILLE, TAILLE, this);
                    }
                }
            }
        }
        int i = 0;
        for (Player p : this.model.getPlayers()) { //dessin des images
            if (this.model.getTour() == i) {
                g.setColor(new Color(254, 255, 255));
                g.fillRect(p.getX() * TAILLE + 3 - 3, p.getY() * TAILLE + 3 - 3, TAILLE, TAILLE);
            }
            if (p instanceof PlayerExplorateur) {
                g.drawImage(imagePlayer[0], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            } else if (p instanceof PlayerIngenieur) {
                g.drawImage(imagePlayer[1], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            } else if (p instanceof PlayerMessager) {
                g.drawImage(imagePlayer[2], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            } else if (p instanceof PlayerNautilus) {
                g.drawImage(imagePlayer[3], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            } else if (p instanceof PlayerPilote) {
                g.drawImage(imagePlayer[4], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            } else if (p instanceof PlayerPlongeur) {
                g.drawImage(imagePlayer[5], p.getX() * TAILLE + 3, p.getY() * TAILLE + 3, TAILLE - 6, TAILLE - 6, this);
                i++;
            }
        }
    }


    /**
     * Retourne la TAILLE
     * @return int
     */
    public int getTaille() {
        return TAILLE;
    }

}
