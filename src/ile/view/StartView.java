package ile.view;

import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class StartView {
    private MenuView menu;
    private JFrame frame;

    /**
     * Constructeur de classe StartView
     * @param model modèle lié au programme
     * @throws IOException
     */
    public StartView(Model model) throws IOException {

        this.frame = new JFrame();
        this.frame.setTitle("L'ile interdite");
        this.frame.setResizable(false);

        this.menu = new MenuView(model,this);

        this.frame.setLayout(new FlowLayout());
        this.frame.add(menu);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
    /**
     * Permet de "fermer" la fenetre
     */
    public void closeWindow(){
        this.frame.setVisible(false);
    }
}
