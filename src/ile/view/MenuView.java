package ile.view;

import ile.controller.MenuController;
import ile.model.Model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel {
    private Model model;
    private StartView startView;
    public final static int WIDTH = 1500;
    public final static int HEIGHT = 900;
    private JLabel title;
    private JLabel joueur1;
    private JLabel joueur2;
    private JLabel joueur3;
    private JLabel joueur4;
    private JLabel joueur5;
    private JLabel joueur6;
    private BufferedImage[] imagePlayer = new BufferedImage[6];
    public JLabel description;
    public int count = 0;

    /**
     * Constructeur de classe
     * @param model modèle lié au programme
     * @param startView la première fenetre à se lancer, correspondant au menu du choix
     * @throws IOException lié aux images
     */
    public MenuView(Model model, StartView startView) throws IOException {
        this.model = model;
        this.startView = startView;

        this.setLayout(null);
        Dimension dim = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dim);

        MenuController ctrl = new MenuController(this.model, this.startView, this);
        addMouseListener(ctrl);
        addMouseMotionListener(ctrl);

        this.title = new JLabel("Choisissez votre personnage (Un personnage par joueur)");
        this.title.setFont(new Font("Calibri", Font.PLAIN, 35));
        Dimension size = this.title.getPreferredSize();
        this.title.setBounds(WIDTH / 2 - size.width / 2, HEIGHT / 8, size.width, size.height);
        this.add(this.title);

        this.joueur1 = new JLabel("Explorateur");
        this.joueur1.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s1 = this.joueur1.getPreferredSize();
        this.joueur1.setBounds(WIDTH / 12, HEIGHT / 2 - 20, s1.width, s1.height);
        this.add(this.joueur1);

        this.joueur2 = new JLabel("Ingénieur");
        this.joueur2.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s2 = this.joueur2.getPreferredSize();
        this.joueur2.setBounds(3 * WIDTH / 12, HEIGHT / 2 - 20, s2.width, s2.height);
        this.add(this.joueur2);

        this.joueur3 = new JLabel("Messager");
        this.joueur3.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s3 = this.joueur3.getPreferredSize();
        this.joueur3.setBounds(5 * WIDTH / 12, HEIGHT / 2 - 20, s3.width, s3.height);
        this.add(this.joueur3);

        this.joueur4 = new JLabel("Nautilus");
        this.joueur4.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s4 = this.joueur4.getPreferredSize();
        this.joueur4.setBounds(7 * WIDTH / 12, HEIGHT / 2 - 20, s4.width, s4.height);
        this.add(this.joueur4);

        this.joueur5 = new JLabel("Pilote");
        this.joueur5.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s5 = this.joueur5.getPreferredSize();
        this.joueur5.setBounds(9 * WIDTH / 12, HEIGHT / 2 - 20, s5.width, s5.height);
        this.add(this.joueur5);

        this.joueur6 = new JLabel("Plongeur");
        this.joueur6.setFont(new Font("Calibri", Font.PLAIN, 16));
        Dimension s6 = this.joueur6.getPreferredSize();
        this.joueur6.setBounds(11 * WIDTH / 12, HEIGHT / 2 - 20, s6.width, s6.height);
        this.add(this.joueur6);

        this.description = new JLabel("");
        this.description.setFont(new Font("Calibri", Font.PLAIN, 20));
        Dimension s = this.description.getPreferredSize();
        this.description.setBounds(WIDTH / 2, HEIGHT / 2 + 200, s.width, s.height);
        this.add(this.description);
        this.description.setVisible(false);

        this.imagePlayer[0] = ImageIO.read(new File("src/files/explo.JPG"));
        this.imagePlayer[1] = ImageIO.read(new File("src/files/inge.JPG"));
        this.imagePlayer[2] = ImageIO.read(new File("src/files/messager.JPG"));
        this.imagePlayer[3] = ImageIO.read(new File("src/files/nautilus.JPG"));
        this.imagePlayer[4] = ImageIO.read(new File("src/files/pilote.JPG"));
        this.imagePlayer[5] = ImageIO.read(new File("src/files/plongeur.JPG"));

    }

    /**
     * Méthode de dessin
     * @param g
     */
    public void paintComponent(Graphics g) {

        g.drawImage(imagePlayer[0], WIDTH / 12, HEIGHT / 2, 75, 75, this);

        g.drawImage(imagePlayer[1], 3 * WIDTH / 12, HEIGHT / 2, 75, 75, this);

        g.drawImage(imagePlayer[2], 5 * WIDTH / 12, HEIGHT / 2, 75, 75, this);

        g.drawImage(imagePlayer[3], 7 * WIDTH / 12, HEIGHT / 2, 75, 75, this);

        g.drawImage(imagePlayer[4], 9 * WIDTH / 12, HEIGHT / 2, 75, 75, this);

        g.drawImage(imagePlayer[5], 11 * WIDTH / 12, HEIGHT / 2, 75, 75, this);

    }

}
