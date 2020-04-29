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
            for (int i = 0 ; i < this.inventory.getDropCases().size() ; i++) {
                for (int j = 0 ; j < this.inventory.getDropCases().get(i).size() ; j++) {
                    if (this.inventory.getDropCases().get(i).get(j).inCase(x, y)){
                        if (j == 0 && this.model.getHand().getKey().get(0) == Key.Air) {
                            this.model.getPlayers().get(i).getKey().add(Key.Air);
                        } else if(j == 1 && this.model.getHand().getKey().get(0) == Key.Water){
                            this.model.getPlayers().get(i).getKey().add(Key.Water);
                        } else if (j == 2 && this.model.getHand().getKey().get(0) == Key.Fire) {
                            this.model.getPlayers().get(i).getKey().add(Key.Fire);
                        } else  {
                            this.model.getPlayers().get(i).getKey().add(Key.Earth);
                        }
                        this.model.getHand().getKey().remove(0);
                    }
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
