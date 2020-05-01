package ile.view;

import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class View {
    private GridView grid;
    private ButtonView command;
    private InventoryView inventory;
    private InfoView info;
    private JFrame frame;
    private Model model;

    /**
     * Constructeur de classe View
     * @param model modèle lié au programme
     * @throws IOException lié aux images
     */
    public View(Model model) throws IOException {

        this.frame = new JFrame();
        this.frame.setTitle("L'ile interdite");
        this.frame.setResizable(false);

        this.grid = new GridView(model, this); //vue correspondant à la grille
        this.command = new ButtonView(model, this.grid, this); //vue correspondant aux boutons
        this.inventory = new InventoryView(model); //vue correspondant à l'inventaire
        this.info = new InfoView(model); //vue correspodant aux informations (textes) à afficher au-dessus
        this.model = model;

        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);

        this.frame.add(grid, BorderLayout.CENTER);
        this.frame.add(command, BorderLayout.PAGE_END);
        this.frame.add(inventory, BorderLayout.EAST);
        this.frame.add(info, BorderLayout.PAGE_START);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);

    }

    /**
     * A afficher quand on perd
     */
    public void endGameLoose() {
        int WIDTH = this.frame.getWidth();
        int HEIGHT = this.frame.getHeight();

        this.frame.getContentPane().removeAll();

        this.frame.setLayout(new BorderLayout());
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel end = new JPanel();
        end.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        end.setLayout(null);

        JLabel label = new JLabel("Perdu, un des joueurs ne s'en est pas sorti...");
        label.setFont(new Font("Calibri", Font.PLAIN, 35));
        Dimension d = label.getPreferredSize();
        label.setBounds(WIDTH / 2 - d.width / 2, HEIGHT / 6, d.width, d.height);
        label.setVisible(true);
        end.add(label);


        JLabel t1 = new JLabel("Joueur 1");
        t1.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d1 = label.getPreferredSize();
        t1.setBounds(WIDTH / 8, HEIGHT / 4, d1.width, d1.height);
        t1.setVisible(true);
        end.add(t1);

        JLabel stats11 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(0).getNbMoves());
        stats11.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s11 = label.getPreferredSize();
        stats11.setBounds(WIDTH / 8 - 30, HEIGHT / 3, s11.width, s11.height);
        stats11.setVisible(true);
        end.add(stats11);

        JLabel stats12 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(0).getNbUnflooded());
        stats12.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s12 = label.getPreferredSize();
        stats12.setBounds(WIDTH / 8 - 30, HEIGHT / 2, s12.width, s12.height);
        stats12.setVisible(true);
        end.add(stats12);

        JLabel t2 = new JLabel("Joueur 2");
        t2.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d2 = label.getPreferredSize();
        t2.setBounds(2 * WIDTH / 8, HEIGHT / 4, d2.width, d2.height);
        t2.setVisible(true);
        end.add(t2);

        JLabel stats21 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(1).getNbMoves());
        stats21.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s21 = label.getPreferredSize();
        stats21.setBounds(2 * WIDTH / 8, HEIGHT / 3, s21.width, s21.height);
        stats21.setVisible(true);
        end.add(stats21);

        JLabel stats22 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(1).getNbUnflooded());
        stats22.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s22 = label.getPreferredSize();
        stats22.setBounds(2 * WIDTH / 8, HEIGHT / 2, s22.width, s22.height);
        stats22.setVisible(true);
        end.add(stats22);

        JLabel t3 = new JLabel("Joueur 3");
        t3.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d3 = label.getPreferredSize();
        t3.setBounds(4 * WIDTH / 8, HEIGHT / 4, d3.width, d3.height);
        t3.setVisible(true);
        end.add(t3);

        JLabel stats31 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(2).getNbMoves());
        stats31.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s31 = label.getPreferredSize();
        stats31.setBounds(4 * WIDTH / 8, HEIGHT / 3, s31.width, s31.height);
        stats31.setVisible(true);
        end.add(stats31);

        JLabel stats32 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(2).getNbUnflooded());
        stats32.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s32 = label.getPreferredSize();
        stats32.setBounds(4 * WIDTH / 8, HEIGHT / 2, s32.width, s32.height);
        stats32.setVisible(true);
        end.add(stats32);

        JLabel t4 = new JLabel("Joueur 4");
        t4.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d4 = label.getPreferredSize();
        t4.setBounds(6 * WIDTH / 8, HEIGHT / 4, d4.width, d4.height);
        t4.setVisible(true);
        end.add(t4);

        JLabel stats41 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(3).getNbMoves());
        stats41.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s41 = label.getPreferredSize();
        stats41.setBounds(6 * WIDTH / 8, HEIGHT / 3, s41.width, s41.height);
        stats41.setVisible(true);
        end.add(stats41);

        JLabel stats42 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(3).getNbUnflooded());
        stats42.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s42 = label.getPreferredSize();
        stats42.setBounds(6 * WIDTH / 8, HEIGHT / 2, s42.width, s42.height);
        stats42.setVisible(true);
        end.add(stats42);


        this.frame.add(end, BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    /**
     * A afficher quand on gagne
     */
    public void endGameWin() {
        int WIDTH = this.frame.getWidth();
        int HEIGHT = this.frame.getHeight();

        this.frame.getContentPane().removeAll();

        this.frame.setLayout(new BorderLayout());
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JPanel end = new JPanel();
        end.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        end.setLayout(null);

        JLabel label = new JLabel("Gagné, vous avez réussi à vous échapper");
        label.setFont(new Font("Calibri", Font.PLAIN, 35));
        Dimension d = label.getPreferredSize();
        label.setBounds(WIDTH / 2 - d.width / 2, HEIGHT / 6, d.width, d.height);
        label.setVisible(true);
        end.add(label);


        JLabel t1 = new JLabel("Joueur 1");
        t1.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d1 = label.getPreferredSize();
        t1.setBounds(WIDTH / 8, HEIGHT / 4, d1.width, d1.height);
        t1.setVisible(true);
        end.add(t1);

        JLabel stats11 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(0).getNbMoves());
        stats11.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s11 = label.getPreferredSize();
        stats11.setBounds(WIDTH / 8 - 30, HEIGHT / 3, s11.width, s11.height);
        stats11.setVisible(true);
        end.add(stats11);

        JLabel stats12 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(0).getNbUnflooded());
        stats12.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s12 = label.getPreferredSize();
        stats12.setBounds(WIDTH / 8 - 30, HEIGHT / 2, s12.width, s12.height);
        stats12.setVisible(true);
        end.add(stats12);

        JLabel t2 = new JLabel("Joueur 2");
        t2.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d2 = label.getPreferredSize();
        t2.setBounds(2 * WIDTH / 8, HEIGHT / 4, d2.width, d2.height);
        t2.setVisible(true);
        end.add(t2);

        JLabel stats21 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(1).getNbMoves());
        stats21.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s21 = label.getPreferredSize();
        stats21.setBounds(2 * WIDTH / 8, HEIGHT / 3, s21.width, s21.height);
        stats21.setVisible(true);
        end.add(stats21);

        JLabel stats22 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(1).getNbUnflooded());
        stats22.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s22 = label.getPreferredSize();
        stats22.setBounds(2 * WIDTH / 8, HEIGHT / 2, s22.width, s22.height);
        stats22.setVisible(true);
        end.add(stats22);

        JLabel t3 = new JLabel("Joueur 3");
        t3.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d3 = label.getPreferredSize();
        t3.setBounds(4 * WIDTH / 8, HEIGHT / 4, d3.width, d3.height);
        t3.setVisible(true);
        end.add(t3);

        JLabel stats31 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(2).getNbMoves());
        stats31.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s31 = label.getPreferredSize();
        stats31.setBounds(4 * WIDTH / 8, HEIGHT / 3, s31.width, s31.height);
        stats31.setVisible(true);
        end.add(stats31);

        JLabel stats32 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(2).getNbUnflooded());
        stats32.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s32 = label.getPreferredSize();
        stats32.setBounds(4 * WIDTH / 8, HEIGHT / 2, s32.width, s32.height);
        stats32.setVisible(true);
        end.add(stats32);

        JLabel t4 = new JLabel("Joueur 4");
        t4.setFont(new Font("Calibri", Font.PLAIN, 25));
        Dimension d4 = label.getPreferredSize();
        t4.setBounds(6 * WIDTH / 8, HEIGHT / 4, d4.width, d4.height);
        t4.setVisible(true);
        end.add(t4);

        JLabel stats41 = new JLabel("Nombre de déplacements : " + this.model.getPlayers().get(3).getNbMoves());
        stats41.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s41 = label.getPreferredSize();
        stats41.setBounds(6 * WIDTH / 8, HEIGHT / 3, s41.width, s41.height);
        stats41.setVisible(true);
        end.add(stats41);

        JLabel stats42 = new JLabel("Nombre de zones asséchées : " + this.model.getPlayers().get(3).getNbUnflooded());
        stats42.setFont(new Font("Calibri", Font.PLAIN, 15));
        Dimension s42 = label.getPreferredSize();
        stats42.setBounds(6 * WIDTH / 8, HEIGHT / 2, s42.width, s42.height);
        stats42.setVisible(true);
        end.add(stats42);


        this.frame.add(end, BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }


}
