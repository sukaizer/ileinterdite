package ile.controller;

import ile.Observer;
import ile.model.Key;
import ile.model.PlayerMessenger;
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


    private BufferedImage keyWater = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kwater.JPG"));
    private BufferedImage keyFire = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kfire.JPG"));
    private BufferedImage keyAir = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kair.JPG"));
    private BufferedImage keyEarth = ImageIO.read(new File("/home/gozea/IleInterdite2/ileinterdite/src/files/kearth.JPG"));


    /**
     * Constructeur de la classe InventoryController
     * @param model le modèle lié au programme
     * @param inventory la vue liée à l'inventaire
     * @throws IOException
     */
    public InventoryController(Model model, InventoryView inventory) throws IOException {
        this.model = model;
        this.inventory = inventory;
    }

    /**
     * Activation du click de la souris pour controller l'inventaire
     * @param e click de la souris
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        inventory.getParent().repaint();
        int x = e.getX();
        int y = e.getY();
        //lache la clé
        if (this.model.getHand().hasKey()) {
            for (int i = 0 ; i < this.inventory.getDropCases().length ; i++) {
                if (this.inventory.getDropCases()[i].inCase(x, y)) {
                    if (this.model.getHand().isNearby(this.model.getPlayers().get(i).getArea()) || this.model.getHand().getFlying()) {
                        this.model.getPlayers().get(i).getKey().add(this.model.getHand().getKey().get(0));
                        this.model.getHand().removeKey();
                        this.model.getHand().setFlying(false);
                        break;
                    }
                }
            }
        } else {
            //prend une clé
            for (int i = 0 ; i < this.inventory.getTakeCases().size() ; i++) {
                for (int j = 0 ; j < this.inventory.getTakeCases().get(i).size() ; j++) {
                    if (this.inventory.getTakeCases().get(i).get(j).inCase(x, y)) {
                        this.model.getHand().addKey(this.model.getPlayers().get(i).positionKey().get(j));
                        //the hand takes the player's coordonates
                        this.model.getHand().setHand(this.model.getPlayers().get(i).getX(), this.model.getPlayers().get(i).getY());
                        this.inventory.getTakeCases().get(i).remove(j);
                        if (this.model.getPlayers().get(i) instanceof PlayerMessenger) this.model.getHand().setFlying(true);
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
