package ile.controller;

import ile.Observer;
import ile.model.Key;
import ile.view.InventoryView;
import ile.model.Model;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InventoryController implements  MouseListener {
    Model model;
    InventoryView inventory;


    private BufferedImage keyWater = ImageIO.read(new File("src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("src/files/kearth.JPG"));


    public InventoryController(Model model, InventoryView inventory) throws IOException {
        this.model = model;
        this.inventory = inventory;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        inventory.getParent().repaint();
        int x = e.getX();
        int y = e.getY();
        //drop a key
        if (this.model.getHand().hasKey()) {
            for (int i = 0 ; i < this.inventory.getDropCases().length ; i++) {
                if (this.inventory.getDropCases()[i].inCase(x, y)) {
                    if (this.model.getHand().isNearby(this.model.getPlayers().get(i).getArea())) {
                        this.model.getPlayers().get(i).getKey().add(this.model.getHand().getKey().get(0));
                        this.model.getHand().removeKey();
                        break;
                    }
                }
            }
        } else {
            //take a key
            for (int i = 0 ; i < this.inventory.getTakeCases().size() ; i++) {
                for (int j = 0 ; j < this.inventory.getTakeCases().get(i).size() ; j++) {
                    if (this.inventory.getTakeCases().get(i).get(j).inCase(x, y)) {
                        this.model.getHand().addKey(this.model.getPlayers().get(i).positionKey().get(j));
                        //the hand takes the player's coordonates
                        this.model.getHand().setHand(this.model.getPlayers().get(i).getX(), this.model.getPlayers().get(i).getY());
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
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {}

    public void paint(Graphics g) {

    }

}
