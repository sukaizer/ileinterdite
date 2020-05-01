package ile.controller;

import ile.model.*;
import ile.view.ButtonView;
import ile.view.GridView;
import ile.view.View;

import java.awt.event.*;
import java.util.ArrayList;


public class Controller implements ActionListener, KeyListener, MouseListener {
    Model model;
    ButtonView buttons;
    GridView grid;
    View view;

    /**
     * Constructeur de classe Controller
     * @param model le modèle lié au programme
     * @param buttons la classe comprenant les boutons
     * @param grid la classe affichant l'interface principale de jeu
     * @param view la vue principale comprenant toutes autres
     */
    public Controller(Model model, ButtonView buttons, GridView grid, View view) {
        this.model = model;
        this.view = view;
        this.buttons = buttons;
        this.grid = grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.getParent().repaint();
        if (e.getSource() == this.buttons.getButtonEndTurnPlayer()) {
            this.model.flooding();
            if (this.model.getTour() == this.model.getPlayers().size() - 1) {
                this.model.flooding();
                for (int i = 0; i < this.model.getPlayers().size(); i++) {
                    this.model.getPlayers().get(i).reset();
                    if (this.model.getPlayers().get(i) instanceof PlayerIngenieur) {
                        this.model.resetFlood((PlayerIngenieur) this.model.getPlayers().get(i));
                    }
                }
            }
            this.model.getPlayers().get(this.model.getTour()).addKey();
            this.model.nextTour();
        }
        if (this.model.testLoose()) {
            this.view.endGameLoose();
        }
        if (this.model.testWin()) this.view.endGameWin();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        grid.getParent().repaint();
        Player player = this.model.getPlayers().get(this.model.getTour());

        if (player instanceof PlayerExplorateur) { //déplacements spéciaux Explorateur
            switch (e.getKeyCode()) {
                case KeyEvent.VK_NUMPAD8:
                    try {
                        Area a = this.model.getArea(player.getX(), player.getY() - 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.UP);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD2:
                    try {
                        Area a = this.model.getArea(player.getX(), player.getY() + 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.DOWN);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD4:
                    try {
                        Area a = this.model.getArea(player.getX() - 1, player.getY());
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.LEFT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD6:
                    try {
                        Area a = this.model.getArea(player.getX() + 1, player.getY());
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.RIGHT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD7:
                    try {
                        Area a = this.model.getArea(player.getX() - 1, player.getY() - 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.UP_LEFT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD9:
                    try {
                        Area a = this.model.getArea(player.getX() + 1, player.getY() - 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.UP_RIGHT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD1:
                    try {
                        Area a = this.model.getArea(player.getX() - 1, player.getY() + 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.DOWN_LEFT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD3:
                    try {
                        Area a = this.model.getArea(player.getX() + 1, player.getY() - 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.DOWN_RIGHT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_A:
                    player.addArtifact();
                    break;
            }
        } else if (player instanceof PlayerPlongeur) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_NUMPAD8:
                    player.Deplacement(Direction.UP);
                    player.setNbMoves();
                    break;
                case KeyEvent.VK_NUMPAD2:
                    player.Deplacement(Direction.DOWN);
                    player.setNbMoves();
                    break;
                case KeyEvent.VK_NUMPAD4:
                    player.Deplacement(Direction.LEFT);
                    player.setNbMoves();
                    break;
                case KeyEvent.VK_NUMPAD6:
                    player.Deplacement(Direction.RIGHT);
                    player.setNbMoves();
                    break;
                case KeyEvent.VK_A:
                    player.addArtifact();
                    break;
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_NUMPAD8:
                    try {
                        Area a = this.model.getArea(player.getX(), player.getY() - 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.UP);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD2:
                    try {
                        Area a = this.model.getArea(player.getX(), player.getY() + 1);
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.DOWN);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD4:
                    try {
                        Area a = this.model.getArea(player.getX() - 1, player.getY());
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.LEFT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_NUMPAD6:
                    try {
                        Area a = this.model.getArea(player.getX() + 1, player.getY());
                        if (!a.getState().equals(State.Submerged)) {
                            player.Deplacement(Direction.RIGHT);
                            player.setNbMoves();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                    break;
                case KeyEvent.VK_A:
                    player.addArtifact();
                    break;
                case KeyEvent.VK_E:
                    if (player instanceof PlayerPilote) {
                        this.model.deplacementPilote((PlayerPilote) player);
                    }
                    break;
            }
        }

        if (this.model.testLoose()) {
            this.view.endGameLoose();
        }
        if (this.model.testWin()) this.view.endGameWin();
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
        Player player = this.model.getPlayers().get(this.model.getTour());

        if (player instanceof PlayerExplorateur) {
            ArrayList<Area> nearby = this.model.getNearby((PlayerExplorateur) player);
            for (Area area : nearby) {
                if (y >= area.getY() * t && y <= area.getY() * t + t && x >= area.getX() * t && x <= area.getX() * t + t) {
                    if (player.hasEnergy()) {
                        int[] a = area.unflood();
                        if (this.model.unflooding(a[0], a[1])) {
                            player.loseEnergy();
                            player.setNbUnflooded();
                        }
                    }
                }
            }

        } else {
            Area[] nearby = this.model.getNearby(player.getArea()); //les cases a coté du joueur dont c'est le tour

            for (Area area : nearby) {
                if (y >= area.getY() * t && y <= area.getY() * t + t && x >= area.getX() * t && x <= area.getX() * t + t) {
                    if (player instanceof PlayerIngenieur) { //classe Ingenieur
                        if (player.hasEnergy() && this.model.getFlood((PlayerIngenieur) player) < 2) {
                            int[] a = area.unflood();
                            if (this.model.unflooding(a[0], a[1])) {
                                if (((PlayerIngenieur) player).getFlood() == 1) {
                                    player.loseEnergy();
                                    player.setNbUnflooded();
                                } else if (((PlayerIngenieur) player).getFlood() == 0) {
                                    this.model.unflooding((PlayerIngenieur) player);
                                    player.setNbUnflooded();
                                }
                            }
                        }
                    }
                    if (player.hasEnergy()) {
                        int[] a = area.unflood();
                        if (player instanceof PlayerNautilus) {
                            if (this.model.unflooding(a[0], a[1], true)) {
                                player.loseEnergy();
                                player.setNbUnflooded();
                            }
                        } else {
                            if (this.model.unflooding(a[0], a[1])) {
                                player.loseEnergy();
                                player.setNbUnflooded();
                            }
                        }
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
