package ile.controller;

import ile.model.*;
import ile.view.ButtonView;
import ile.view.GridView;
import ile.view.View;

import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class Controller implements ActionListener, KeyListener, MouseListener {
    Model model;
    ButtonView buttons;
    GridView grid;
    View view;

    public Controller(Model model,ButtonView buttons,GridView grid, View view) {
        this.model = model;
        this.view = view;
        this.buttons = buttons;
        this.grid = grid;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        grid.getParent().repaint();
        if(e.getSource() == this.buttons.getButtonEndTurnPlayer()){
            this.model.flooding();
            if(this.model.getTour() == this.model.getPlayers().size() - 1){
                this.model.flooding();
                for (int i = 0 ; i < this.model.getPlayers().size(); i++) {
                    this.model.getPlayers().get(i).reset();
                    if(this.model.getPlayers().get(i) instanceof PlayerIngenieur){
                        this.model.resetFlood((PlayerIngenieur) this.model.getPlayers().get(i));
                    }
                }
            }
            this.model.getPlayers().get(this.model.getTour()).addKey();
            this.model.nextTour();
        }
        if(this.model.testLoose()) this.view.endGameLoose();
        if(this.model.testWin()) this.view.endGameWin();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        grid.getParent().repaint();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.model.getPlayers().get(this.model.getTour()).Deplacement(Direction.UP); //valable pour les 4 joueurs (future feature)
                break;
            case KeyEvent.VK_DOWN:
                this.model.getPlayers().get(this.model.getTour()).Deplacement(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                this.model.getPlayers().get(this.model.getTour()).Deplacement(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.model.getPlayers().get(this.model.getTour()).Deplacement(Direction.RIGHT);
                break;
            case KeyEvent.VK_A:
                this.model.getPlayers().get(this.model.getTour()).probArtifact();
                break;
            case KeyEvent.VK_E:
                if(this.model.getPlayers().get(this.model.getTour()) instanceof PlayerPilote)
                this.model.deplacementPilote((PlayerPilote) this.model.getPlayers().get(this.model.getTour()));
                break;
        }
        if(this.model.testLoose()) this.view.endGameLoose();
        if(this.model.testWin()) this.view.endGameWin();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        grid.getParent().repaint();
        int x = e.getX();
        int y = e.getY();
        int t = this.grid.getTaille();

        if(this.model.getPlayers().get(this.model.getTour()) instanceof PlayerExplorateur){
            ArrayList<Area> nearby = this.model.getNearby((PlayerExplorateur) this.model.getPlayers().get(this.model.getTour()));
            for (Area area : nearby) {
                if (y >= area.getY() * t && y <= area.getY() * t + t && x >= area.getX() * t && x <= area.getX() * t + t) {
                    if(this.model.getPlayers().get(this.model.getTour()).hasEnergy()){
                        int[] a = area.unflood();
                        this.model.getPlayers().get(this.model.getTour()).loseEnergy();
                        this.model.unflooding(a[0], a[1]);
                    }
                }
            }

        }else{
            Area[] nearby = this.model.getNearby(this.model.getPlayers().get(this.model.getTour()).getArea()); //les cases a coté du joueur dont c'est le tour

            for (Area area : nearby) {
                if (y >= area.getY() * t && y <= area.getY() * t + t && x >= area.getX() * t && x <= area.getX() * t + t) {
                    if(this.model.getPlayers().get(this.model.getTour()) instanceof PlayerIngenieur){ //classe Ingenieur
                        if(this.model.getPlayers().get(this.model.getTour()).hasEnergy() && this.model.getFlood((PlayerIngenieur) this.model.getPlayers().get(this.model.getTour())) < 2){
                            int[] a = area.unflood();
                            this.model.getPlayers().get(this.model.getTour()).loseEnergy();
                            this.model.unflooding(a[0], a[1]);
                            this.model.unflooding((PlayerIngenieur) this.model.getPlayers().get(this.model.getTour()));
                        }
                    }
                    if(this.model.getPlayers().get(this.model.getTour()).hasEnergy()){
                        int[] a = area.unflood();
                        this.model.getPlayers().get(this.model.getTour()).loseEnergy();
                        this.model.unflooding(a[0], a[1]);
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
