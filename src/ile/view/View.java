package ile.view;
import ile.model.Model;

import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View {
    private GridView grid;
    private ButtonView command;
    private InventoryView inventory;
    private InfoView info;
    private JFrame frame;

    /** Construction d'une vue attachée à un modèle. */
    public View(Model model) throws IOException {
        /** Définition de la fenêtre principale. */
        this.frame = new JFrame();
        this.frame.setTitle("L'ile interdite");
        this.frame.setLayout(new BorderLayout());

        this.grid = new GridView(model,this); //vue correspondant à la grille
        this.frame.add(grid,BorderLayout.CENTER);

        this.command = new ButtonView(model,this.grid,this); //vue correspondant aux boutons
        this.frame.add(command,BorderLayout.PAGE_END);

        this.inventory = new InventoryView(model); //vue correspondant à l'inventaire
        this.frame.add(inventory,BorderLayout.EAST);

        this.info = new InfoView(model); //vue correspodant aux informations (textes) à afficher au-dessus
        this.frame.add(info,BorderLayout.PAGE_START);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);

    }

    public void endGameLoose(){ //à améliorer bien sur
        int WIDTH = this.frame.getWidth();
        int HEIGHT = this.frame.getHeight();

        this.frame.getContentPane().removeAll();
        this.frame.setLayout(new BorderLayout());
        this.frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        JPanel end = new JPanel();
        end.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        end.setLayout(null);
        JLabel label = new JLabel("Perdu, un des joueurs ne s'en est pas sorti...");
        label.setBounds(WIDTH/2,HEIGHT/4,500,500);
        label.setVisible(true);
        end.add(label);
        this.frame.add(end,BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void endGameWin(){ //à améliorer bien sur
        int WIDTH = this.frame.getWidth();
        int HEIGHT = this.frame.getHeight();

        this.frame.getContentPane().removeAll();
        this.frame.setLayout(new BorderLayout());
        this.frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        JPanel end = new JPanel();
        end.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        end.setLayout(null);
        JLabel label = new JLabel("Bravo vous avez gagné !");
        label.setBounds(WIDTH/2,HEIGHT/4,500,500);
        label.setVisible(true);
        end.add(label);
        this.frame.add(end,BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }


}
