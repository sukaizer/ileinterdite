package ile.controller;

import ile.model.Key;
import ile.view.InventoryView;
import ile.model.Model;

import java.awt.event.*;
import java.util.ArrayList;

public class InventoryController implements  MouseListener {
    Model model;
    InventoryView inventory;

    public InventoryController(Model model, InventoryView inventory) {
        this.model = model;
        this.inventory = inventory;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //drop a key
        if (this.model.getHand().hasKey()) {
            for (int i = 0 ; i < this.inventory.getDropCases().length ; i++) {
                if (this.inventory.getDropCases()[i].inCase(x, y)){
                    this.model.getPlayers().get(i).getKey().add(this.model.getHand().getKey().get(0));
                    this.model.getHand().getKey().remove(0);
                    break;
                }
            }
        } else {
            //take a key
            for (int i = 0 ; i < this.inventory.getTakeCases().size() ; i++) {
                for (int j = 0 ; j < this.inventory.getTakeCases().get(i).size() ; j++) {
                    if (this.inventory.getTakeCases().get(i).get(j).inCase(x, y)) {
                        this.model.getHand().addKey(this.model.getPlayers().get(i).positionKey().get(j));
                        for (Key k : this.model.getPlayers().get(i).getKey()) {
                            if (k == this.model.getPlayers().get(i).positionKey().get(j)) {
                                this.model.getPlayers().get(i).getKey().remove(k);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}





}
