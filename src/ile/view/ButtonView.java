package ile.view;

import ile.controller.Controller;
import ile.model.Direction;
import ile.model.Model;
import ile.model.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonView extends JPanel {

    private Model model;
    private GridView grid;
    JButton buttonEndTurnPlayer;

    public ButtonView(Model model,GridView grid) {
        this.model = model;
        this.grid = grid;
        this.buttonEndTurnPlayer = new JButton("Fin de tour");
        this.add(buttonEndTurnPlayer);
        Controller ctrl = new Controller(model,this,this.grid);
        addKeyListener(ctrl);
        buttonEndTurnPlayer.addKeyListener(ctrl);
        buttonEndTurnPlayer.addActionListener(ctrl);
        setFocusable(true);
    }


    public JButton getButtonEndTurnPlayer(){
        return this.buttonEndTurnPlayer;
    }
}
