package ile.view;

import ile.controller.Controller;
import ile.model.Model;

import javax.swing.*;

public class ButtonView extends JPanel {

    private Model model;
    private GridView grid;
    private View view;
    JButton buttonEndTurnPlayer;

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


    public JButton getButtonEndTurnPlayer() {
        return this.buttonEndTurnPlayer;
    }
}
