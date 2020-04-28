package ile.view;
import ile.model.Model;

import java.io.IOException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame {
    private GridView grid;
    private ButtonView command;
    private InventoryView inventory;
    private InfoView info;

    /** Construction d'une vue attachée à un modèle. */
    public View(Model model) throws IOException {
        /** Définition de la fenêtre principale. */

        this.setTitle("L'ile interdite");
        this.setLayout(new BorderLayout());

        this.grid = new GridView(model); //vue correspondant à la grille
        this.add(grid,BorderLayout.CENTER);

        command = new ButtonView(model,this.grid); //vue correspondant aux boutons
        this.add(command,BorderLayout.PAGE_END);

        this.inventory = new InventoryView(model); //vue correspondant à l'inventaire
        this.add(inventory,BorderLayout.EAST);

        this.info = new InfoView(model); //vue correspodant aux informations (textes) à afficher au-dessus
        this.add(info,BorderLayout.PAGE_START);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void updateContentFrame(){
        //this.getContentPane().removeAll();
        //this.getContentPane().revalidate();
        //this.getContentPane().repaint();
    }

}
