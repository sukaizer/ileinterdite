package ile.view;

import ile.controller.Controller;
import ile.model.Model;

import javax.swing.*;

public class ButtonView extends JPanel {

    private Model model;
    private GridView grid;
    private View view;
    JButton buttonEndTurnPlayer;

    /**
     * Constructeur de classe ButtonView
     * @param model modèle lié au programme
     * @param grid la classe affichant l'interface principale de jeu
     * @param view la vue principale comprenant toutes autres
     */
    public ButtonView(Model model, GridView grid, View view) {
        this.model = model;
        this.grid = grid;
        this.view = view;
        this.buttonEndTurnPlayer = new JButton("Fin de tour");
        this.add(buttonEndTurnPlayer);
        Controller ctrl = new Controller(model, this, this.grid, this.view);
        addKeyListener(ctrl);
        buttonEndTurnPlayer.addKeyListener(ctrl);
        buttonEndTurnPlayer.addActionListener(ctrl);
        setFocusable(true);
    }

    /**
     * Retourne le bouton
     * @return JButton
     */
    public JButton getButtonEndTurnPlayer() {
        return this.buttonEndTurnPlayer;
    }
}
